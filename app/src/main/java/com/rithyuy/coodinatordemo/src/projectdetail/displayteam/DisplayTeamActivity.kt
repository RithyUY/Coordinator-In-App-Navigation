package com.rithyuy.coodinatordemo.src.projectdetail.displayteam

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.github.chrisbanes.photoview.PhotoView
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.adapter.RecyclerViewAdapter
import com.rithyuy.coodinatordemo.base.BaseActivity
import com.rithyuy.coodinatordemo.base.ViewHolderProvider
import com.rithyuy.coodinatordemo.di.AppComponent
import kotlinx.android.synthetic.main.activity_display_team.*

/****
 *
 * Created by UY RITHY on 12/20/18.
 *
 */

class DisplayTeamActivity : BaseActivity<DisplayTeamViewModel>(DisplayTeamViewModel::class.java), ViewHolderProvider<ImageViewHolder> {

    private val pagerSnapHelper by lazy { PagerSnapHelper() }
    private lateinit var mAdapter: RecyclerViewAdapter<ImageViewHolder>
    override val layoutResource: Int get() = R.layout.activity_display_team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun setupComponent(appComponent: AppComponent) {
        appComponent.plus(viewModel)
        bindView()
    }

    private fun bindView() {
        val teams = arrayListOf<Any>().apply { addAll(viewModel.adapterItems) }
        mAdapter = RecyclerViewAdapter(teams, this)
        recyclerView.layoutManager =LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = mAdapter
        pagerSnapHelper.attachToRecyclerView(recyclerView)
        recyclerView.scrollToPosition(viewModel.displayIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val imageView = PhotoView(this)
        imageView.setBackgroundColor(Color.BLACK)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return ImageViewHolder(imageView)
    }
}