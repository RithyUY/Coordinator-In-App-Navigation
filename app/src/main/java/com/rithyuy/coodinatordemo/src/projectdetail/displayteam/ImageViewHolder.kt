package com.rithyuy.coodinatordemo.src.projectdetail.displayteam

import android.view.View
import com.github.chrisbanes.photoview.PhotoView
import com.rithyuy.coodinatordemo.base.BaseViewHolder
import com.rithyuy.coodinatordemo.model.Team
import com.squareup.picasso.Picasso

/****
 *
 * Created by UY RITHY on 12/20/18.
 *
 */

class ImageViewHolder(view: View) : BaseViewHolder(view) {

    override fun onBind(data: Any) {
        (data as? Team)?.apply { bind(this) }
    }

    private fun bind(team: Team) {
        Picasso.get().load(team.imageUrl).into((itemView as PhotoView))
    }
}