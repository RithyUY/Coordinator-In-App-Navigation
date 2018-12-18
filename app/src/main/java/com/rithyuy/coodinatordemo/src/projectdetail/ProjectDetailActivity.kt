package com.rithyuy.coodinatordemo.src.projectdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.base.BaseView

/****
 *
 * Created by UY RITHY on 12/18/18.
 *
 */

class ProjectDetailActivity : AppCompatActivity(), BaseView {

    override val layoutResource: Int = R.layout.activity_project_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
    }

    override fun onBackPressed() {
        finish()
    }
}