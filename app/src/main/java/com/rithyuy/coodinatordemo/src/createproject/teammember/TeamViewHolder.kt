package com.rithyuy.coodinatordemo.src.createproject.teammember

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import com.rithyuy.coodinatordemo.base.BaseViewHolder
import com.rithyuy.coodinatordemo.model.SelectableTeam
import com.rithyuy.coodinatordemo.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_team.view.*

class TeamViewHolder(view: View) : BaseViewHolder(view) {

    override fun onBind(data: Any) {
        (data as? SelectableTeam)?.apply { bind(this) }
        (data as? Team)?.apply { bind(this) }
    }

    @SuppressLint("CheckResult")
    private fun bind(data: SelectableTeam) {
        val filterColor = if (data.isSelect) Color.parseColor("#6CFFFFFF") else Color.TRANSPARENT
        itemView.imgTeam.setColorFilter(filterColor)
        itemView.imgCheck.visibility = if (data.isSelect) View.VISIBLE else View.GONE
        data.team.apply {
            itemView.tvName.text = name
            Picasso.get().load(imageUrl).into(itemView.imgTeam)
        }
    }

    private fun bind(team: Team) {
        Picasso.get().load(team.imageUrl).into(itemView.imgTeam)
        itemView.tvName.text = team.name
        itemView.imgCheck.visibility = View.GONE
    }
}