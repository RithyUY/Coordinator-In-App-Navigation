package com.rithyuy.coodinatordemo.adapter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.*

/****
 *
 * Created by UY RITHY on 12/20/18.
 *
 */

class ItemDragCallBack(private val items: ArrayList<Any>,
                       private val recyclerView: RecyclerView,
                       dragDirs: Int, swipeDirs: Int) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    val onSwapItems: Subject<Pair<Int, Int>> by lazy { PublishSubject.create<Pair<Int, Int>>() }
    val onDeletedItem: Subject<Int> by lazy { PublishSubject.create<Int>() }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition)
                Collections.swap(items, i, i + 1)

        } else {
            for (i in fromPosition downTo toPosition + 1)
                Collections.swap(items, i, i - 1)
        }

        onSwapItems.onNext(Pair(fromPosition, toPosition))
        recyclerView.adapter?.apply { notifyItemMoved(fromPosition, toPosition) }
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val removedPosition: Int = viewHolder.adapterPosition
        items.removeAt(removedPosition)
        recyclerView.adapter?.notifyItemRemoved(removedPosition)
        onDeletedItem.onNext(removedPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.apply {
            val animator = AnimatorSet()
            animator.play(ObjectAnimator.ofFloat(itemView, View.SCALE_X, 1f, 1.2f))
                    .with(ObjectAnimator.ofFloat(itemView, View.SCALE_Y, 1f, 1.2f))
            animator.start()
        }
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        viewHolder?.itemView?.apply {
            if ((scaleX > 1f) and !isCurrentlyActive) {
                val animator = AnimatorSet()
                animator.play(ObjectAnimator.ofFloat(this, View.SCALE_X, 1.2f, 1f))
                        .with(ObjectAnimator.ofFloat(this, View.SCALE_Y, 1.2f, 1f))
                animator.start()
            }
        }
    }
}