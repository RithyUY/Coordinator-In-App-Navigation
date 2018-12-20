package com.rithyuy.coodinatordemo.src.projectdetail

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.rithyuy.coodinatordemo.App
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.adapter.ItemDragCallBack
import com.rithyuy.coodinatordemo.adapter.RecyclerViewAdapter
import com.rithyuy.coodinatordemo.base.BaseActivity
import com.rithyuy.coodinatordemo.base.ViewHolderProvider
import com.rithyuy.coodinatordemo.di.AppComponent
import com.rithyuy.coodinatordemo.model.Team
import com.rithyuy.coodinatordemo.src.createproject.teammember.TeamViewHolder
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_project_detail.*
import kotlinx.android.synthetic.main.view_holder_team.view.*
import java.util.*


/****
 *
 * Created by UY RITHY on 12/18/18.
 *
 */

class ProjectDetailActivity : BaseActivity<ProjectDetailViewModel>(ProjectDetailViewModel::class.java), ViewHolderProvider<TeamViewHolder> {

    override val layoutResource: Int = R.layout.activity_project_detail
    private val disposables = arrayListOf<Disposable>()
    private lateinit var mAdapter: RecyclerViewAdapter<TeamViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureWindow()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun configureWindow() {
        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.WHITE
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    override fun setupComponent(appComponent: AppComponent) {
        appComponent.plus(viewModel)
        tvProjectName.text = viewModel.projectName
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val items = arrayListOf<Any>()
        items.addAll(viewModel.teams)
        mAdapter = RecyclerViewAdapter(items, this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = mAdapter
        disposables.add(mAdapter.onItemClickListener.subscribe(this::itemClicked))
        val itemDragCallBack = ItemDragCallBack(mAdapter.items, recyclerView,
                ItemTouchHelper.LEFT or
                        ItemTouchHelper.RIGHT or
                        ItemTouchHelper.UP or
                        ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT)

        val itemTouchHelper = ItemTouchHelper(itemDragCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        disposables.add(itemDragCallBack.onSwapItems.subscribe { swapItems(it.first, it.second) })
        disposables.add(itemDragCallBack.onDeletedItem.subscribe { viewModel.teams.removeAt(it) })
    }

    private fun swapItems(fromPos: Int, toPos: Int) {
        if (fromPos < toPos) {
            for (i in fromPos until toPos) Collections.swap(viewModel.teams, i, i + 1)
        } else {
            for (i in fromPos downTo toPos + 1) Collections.swap(viewModel.teams, i, i - 1)
        }
    }

    private fun itemClicked(position: Int) {
        (mAdapter.items[position] as? Team)?.apply {
            (application as App).appFlow.createProject.displayTeamAt(position)
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = layoutInflater.inflate(R.layout.view_holder_team, parent, false)
        val width = resources.displayMetrics.widthPixels
        val itemSize = (width / 3) - viewPadding()
        (itemView as ViewGroup).getChildAt(0).apply {
            layoutParams.width = itemSize
            layoutParams.height = itemSize
            imgTeam.clipToOutline = true
        }
        return TeamViewHolder(itemView)
    }

    private fun viewPadding(): Int {
        return ((resources.displayMetrics.widthPixels / 3) * 0.25).toInt()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach { it.dispose() }
    }
}