package com.rithyuy.coodinatordemo.src.createproject.projectname

import android.os.Bundle
import android.view.View
import com.rithyuy.coodinatordemo.R
import com.rithyuy.coodinatordemo.base.BaseFragment
import com.rithyuy.coodinatordemo.di.AppComponent
import com.rithyuy.coodinatordemo.src.createproject.FragmentHostNavigator
import kotlinx.android.synthetic.main.fragment_project_name.view.*

class ProjectNameFragment : BaseFragment<ProjectNameViewModel>(ProjectNameViewModel::class.java) {

    private var hostNavigator: FragmentHostNavigator?=null

    fun bindHost(hostNavigator: FragmentHostNavigator) : ProjectNameFragment {
        this.hostNavigator = hostNavigator
        return this
    }

    override val layoutResource: Int get() = R.layout.fragment_project_name

    override fun setupAppComponent(appComponent: AppComponent) {
        appComponent.plus(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnGetStarted.setOnClickListener { hostNavigator?.moveNextOf(this) }
        view.edProjectName.addTextChangedListener(viewModel.projectNameTextChange)
    }
}