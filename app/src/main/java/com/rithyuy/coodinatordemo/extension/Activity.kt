package com.rithyuy.coodinatordemo.extension

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair

fun AppCompatActivity.startShareElememt(target: Class<out AppCompatActivity>,vararg sharedElement: Pair<View, String>) {
    val option = ActivityOptionsCompat
        .makeSceneTransitionAnimation(this, *sharedElement)
    startActivity(Intent(this, target), option.toBundle())
}