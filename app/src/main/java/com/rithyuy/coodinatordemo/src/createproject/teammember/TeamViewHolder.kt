package com.rithyuy.coodinatordemo.src.createproject.teammember

import android.graphics.Color
import android.view.View
import com.rithyuy.coodinatordemo.base.BaseViewHolder
import com.rithyuy.coodinatordemo.model.SelectableTeam
import kotlinx.android.synthetic.main.view_holder_team.view.*

class TeamViewHolder(view: View) : BaseViewHolder(view) {

    override fun onBind(data: Any) {
        (data as? SelectableTeam)?.apply { bind(this) }
    }

    private fun bind(data: SelectableTeam) {
        itemView.name.text = data.team.name
        val color = if(data.isSelect) Color.WHITE else Color.parseColor("#17ff6f00")
        itemView.setBackgroundColor(color)
    }
}