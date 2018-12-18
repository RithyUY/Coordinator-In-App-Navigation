package com.rithyuy.coodinatordemo.src.main

import androidx.recyclerview.widget.RecyclerView
import com.rithyuy.coodinatordemo.App
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.base.BaseActivity
import com.rithyuy.coodinatordemo.coodinator.flows.SignUpFlow
import com.rithyuy.coodinatordemo.di.AppComponent
import com.rithyuy.coodinatordemo.extension.bind
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class.java) {

    private val signUpFlow: SignUpFlow get() = (application as App).appFlow.signUpFlow
    private val disposables = arrayListOf<Disposable>()
    override val layoutResource: Int get() = R.layout.activity_main

    override fun setupComponent(appComponent: AppComponent) {
        appComponent.plus(viewModel)
        bindView()
    }

    private fun bindView() {
        disposables.add(tvHello.bind(viewModel.usernameField))
        tvHello.setOnClickListener { signUpFlow.submitUsername("Rithy") }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.forEach { disposable -> disposable.dispose() }
    }
}