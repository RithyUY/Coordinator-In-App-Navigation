package com.rithyuy.coodinatordemo.src.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rithyuy.coodinatordemo.App
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.coodinator.flows.SignUpData
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class SignUpActivity : AppCompatActivity(){

    @Inject
    lateinit var signUpData: SignUpData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).appComponent.plus(this)
        tvHello.text = signUpData.username
    }
}