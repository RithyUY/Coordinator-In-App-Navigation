package com.rithyuy.coodinatordemo.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.rithyuy.coodinatordemo.App
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.di.AppComponent

abstract class BaseActivity<VM : BaseViewModel<*>>(targetVM: Class<VM>) : AppCompatActivity(), BaseView {

    protected val viewModel: VM by lazy { ViewModelProviders.of(this).get(targetVM) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        (application as? App)?.apply { setupComponent(appComponent) }
        viewModel.onModelInit()
    }

    abstract fun setupComponent(appComponent: AppComponent)

    override fun onResume() {
        super.onResume()
        (application as? App)?.navigator?.delegateTo(this)
    }
}