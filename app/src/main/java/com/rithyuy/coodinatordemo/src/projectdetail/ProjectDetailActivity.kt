package com.rithyuy.coodinatordemo.src.projectdetail

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.adapter.RecyclerViewAdapter
import com.rithyuy.coodinatordemo.base.BaseActivity
import com.rithyuy.coodinatordemo.base.ViewHolderProvider
import com.rithyuy.coodinatordemo.di.AppComponent
import com.rithyuy.coodinatordemo.extension.toast
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
        window.statusBarColor = Color.WHITE
    }

    override fun setupComponent(appComponent: AppComponent) {
        appComponent.plus(viewModel)
        tvProjectName.text = viewModel.projectName
        setupRecyclerView()
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupRecyclerView() {
        val items = arrayListOf<Any>()
        items.addAll(viewModel.teams)
        mAdapter = RecyclerViewAdapter(items, this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = mAdapter
        disposables.add(mAdapter.onItemClickListener.subscribe(this::itemClicked))
    }

    private fun itemClicked(position: Int) {
        ( mAdapter.items[position] as? Team)?.apply { toast(name) }
    }

    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(mAdapter.items, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(mAdapter.items, i, i - 1)
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val svH = viewHolder as TeamViewHolder
            val item = mAdapter.items[svH.adapterPosition]
            val index = mAdapter.items.indexOf(item)
            mAdapter.items.remove(item)
            mAdapter.notifyItemRemoved(index)
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
            this.imgTeam.clipToOutline = true
        }
        return TeamViewHolder(itemView)
    }

    private fun viewPadding() : Int {
        return ((resources.displayMetrics.widthPixels / 3) * 0.25).toInt()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach { it.dispose() }
    }
}