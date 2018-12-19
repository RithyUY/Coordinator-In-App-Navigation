package com.rithyuy.coodinatordemo.src.createproject.launcproject

import com.rithyuy.coodinatordemo.base.BaseModel
import com.rithyuy.coodinatordemo.base.BaseViewModel
import com.rithyuy.coodinatordemo.coodinator.flows.CreateProjectFlow
import javax.inject.Inject

/****
 *
 * Created by UY RITHY on 12/19/18.
 *
 */

class LaunchProjectViewModel : BaseViewModel<LaunchProjectModel>() {

    val projectNameChange get() = dataModel.onProjectNameChange
}

class LaunchProjectModel @Inject constructor(private val projectFlow: CreateProjectFlow) : BaseModel() {

    val onProjectNameChange get() = projectFlow.onProjectNameChange
}