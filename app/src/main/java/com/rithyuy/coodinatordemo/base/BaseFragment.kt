package com.rithyuy.coodinatordemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rithyuy.coodinatordemo.App
import com.rithyuy.coodinatordemo.di.AppComponent

abstract class BaseFragment<VM: BaseViewModel<*>>(target: Class<VM>) : Fragment(), BaseView {

    protected val viewModel: VM by lazy { ViewModelProviders.of(this).get(target) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.apply {
            setupAppComponent((application as App).appComponent)
            viewModel.onModelInit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResource, container, false)
    }

    abstract fun setupAppComponent(appComponent: AppComponent)
}