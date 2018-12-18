package com.rithyuy.coodinatordemo.extension

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

fun AppCompatActivity.startShareElememt(target: Class<out AppCompatActivity>,vararg sharedElement: Pair<View, String>, clearTop : Boolean = false)  {
    val intent = Intent(this, target)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    val option = ActivityOptionsCompat
        .makeSceneTransitionAnimation(this, *sharedElement)
    startActivity(intent , option.toBundle())
    if (clearTop) finish()
}