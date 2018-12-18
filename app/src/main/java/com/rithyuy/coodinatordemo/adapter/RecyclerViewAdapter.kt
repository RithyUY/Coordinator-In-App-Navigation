package com.rithyuy.coodinatordemo.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rithyuy.coodinatordemo.base.BaseViewHolder
import com.rithyuy.coodinatordemo.base.ViewHolderProvider
import com.rithyuy.coodinatordemo.base.ViewHolderViewTypeProvider
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class RecyclerViewAdapter<VH : BaseViewHolder>(
        private val items: ArrayList<Any>,
        private val viewHolderProvider: ViewHolderProvider<VH>,
        private val viewTypeProvider: ViewHolderViewTypeProvider? = null) : RecyclerView.Adapter<VH>() {

    var onItemClickListener: Subject<Int> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return viewHolderProvider.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(items[position])
        holder.itemView.setOnClickListener { onItemClickListener.onNext(position) }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = viewTypeProvider?.viewHolderType(position) ?: 0
}