package com.rithyuy.coodinatordemo.src.projectdetail.displayteam

import com.rithyuy.coodinatordemo.base.BaseModel
import com.rithyuy.coodinatordemo.base.BaseViewModel
import com.rithyuy.coodinatordemo.coodinator.flows.CreateProjectFlow
import javax.inject.Inject

/****
 *
 * Created by UY RITHY on 12/20/18.
 *
 */

class DisplayTeamViewModel : BaseViewModel<DisplayTeamModel>() {

    val adapterItems get() = dataModel.teams
    val displayIndex get() = dataModel.displayIndex
}

class DisplayTeamModel @Inject constructor(private val projectFlow: CreateProjectFlow) : BaseModel() {

    val teams get() = projectFlow.data.initialTeam
    val displayIndex get() = projectFlow.data.firstDisplayIndex
}