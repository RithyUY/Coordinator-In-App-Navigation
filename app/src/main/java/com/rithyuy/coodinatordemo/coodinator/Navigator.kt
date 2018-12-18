package com.rithyuy.coodinatordemo.coodinator

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(){

    private lateinit var activity: AppCompatActivity

    fun delegateTo(activity: AppCompatActivity) {
        this.activity = activity
        Log.d("${this::class.java.simpleName.toUpperCase()}_TAG", activity::class.java.simpleName)
    }

    fun navigate(targetActivity: Class<out AppCompatActivity>) {
        if(::activity.isInitialized) {
            val intent = Intent(activity, targetActivity)
            activity.startActivity(intent)
        }
    }
}