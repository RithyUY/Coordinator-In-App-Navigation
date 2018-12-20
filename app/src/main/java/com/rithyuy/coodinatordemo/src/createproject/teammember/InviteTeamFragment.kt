package com.rithyuy.coodinatordemo.src.createproject.teammember

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.adapter.RecyclerViewAdapter
import com.rithyuy.coodinatordemo.base.BaseFragment
import com.rithyuy.coodinatordemo.base.BaseView
import com.rithyuy.coodinatordemo.base.ViewHolderProvider
import com.rithyuy.coodinatordemo.di.AppComponent
import com.rithyuy.coodinatordemo.extension.bind
import com.rithyuy.coodinatordemo.src.createproject.FragmentHostNavigator
import com.rithyuy.coodinatordemo.util.AnimationUtil
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_invite_team.*
import kotlinx.android.synthetic.main.fragment_invite_team.view.*
import kotlinx.android.synthetic.main.view_holder_team.view.*

class InviteTeamFragment : BaseFragment<InviteTeamViewModel>(InviteTeamViewModel::class.java), BaseView, ViewHolderProvider<TeamViewHolder> {

    private lateinit var hostNavigator: FragmentHostNavigator
    private val disposables = arrayListOf<Disposable>()

    private lateinit var adapter: RecyclerViewAdapter<TeamViewHolder>

    fun bindHost(hostNavigator: FragmentHostNavigator): InviteTeamFragment {
        this.hostNavigator = hostNavigator
        return this
    }

    override fun setupAppComponent(appComponent: AppComponent) {
        appComponent.plus(viewModel)
    }

    override val layoutResource: Int get() = R.layout.fragment_invite_team

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        view.btnSkip.setOnClickListener { nextClick() }
        view.btnAdd.setOnClickListener { animateShowList(view) }
        view.btnNext.setOnClickListener{ nextClick() }
        tvProjectName.bind(viewModel.onProjectNameChange)
        rvMember.addOnScrollListener(recyclerScrolled)
    }

    private val recyclerScrolled = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val visibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
            header.elevation = if(visibleItem ==0) 0f else resources.getDimension(R.dimen.dimen_4dp)
        }
    }

    private fun nextClick() {
        hostNavigator.moveNextOf(this)
        viewModel.submitInitialTeam()
    }

    private fun setupRecyclerView() {
        val items = arrayListOf<Any>()
        items.addAll(viewModel.teams)
        adapter = RecyclerViewAdapter(items, this)
        view?.apply {
            this.rvMember.layoutManager = GridLayoutManager(context, 3)
            this.rvMember.adapter = adapter
        }

        disposables.add(adapter.onItemClickListener.subscribe(this::itemClick))
    }

    private fun itemClick(position: Int) {
        val item = viewModel.teams[position]
        item.isSelect = !item.isSelect
        adapter.notifyItemChanged(position)
        val selectedCount = viewModel.teams.filter { it.isSelect }.count()
        view?.btnNext?.apply { text = if (selectedCount > 0) "Selected $selectedCount" else "Next" }
    }

    @SuppressLint("CheckResult")
    private fun animateShowList(view: View) {
        val animatorSet = AnimatorSet()
        animatorSet.duration = 250
        view.rvMember.visibility = View.VISIBLE
        view.header.visibility = View.VISIBLE
        val sliderEmptyStateView = ObjectAnimator.ofFloat(view.viewEmptyState, View.ALPHA, 1f, 0f)
        val slideList = ObjectAnimator.ofFloat(view.rvMember, View.TRANSLATION_Y, 200f, 0f)
        val slideHeader = ObjectAnimator.ofFloat(view.header, View.TRANSLATION_Y, -100f, 0f)
        animatorSet.play(sliderEmptyStateView).with(slideList).with(slideHeader)
        AnimationUtil.animate(animatorSet).subscribe({}, {}, { view.viewEmptyState.visibility = View.GONE })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = layoutInflater.inflate(R.layout.view_holder_team, parent, false)
        val width =  resources.displayMetrics.widthPixels
        val itemSize = ((width/3) - (resources.displayMetrics.widthPixels / 3) * 0.25).toInt()
        (itemView as ViewGroup).getChildAt(0).apply {
            layoutParams.width = itemSize
            layoutParams.height = itemSize
            this.imgTeam.clipToOutline = true
        }
        return TeamViewHolder(itemView)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach { it.dispose() }
    }
}