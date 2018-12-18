package com.rithyuy.coodinatordemo.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(data: Any)
}

interface ViewHolderProvider<VH: BaseViewHolder> {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : VH
}

interface ViewHolderViewTypeProvider {

    fun viewHolderType(position: Int) : Int
}

interface ItemClickListener {

    fun onItemClicked(position: Int)
}