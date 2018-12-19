package com.rithyuy.coodinatordemo.src.projectdetail

import com.rithyuy.coodinatordemo.base.BaseModel
import com.rithyuy.coodinatordemo.coodinator.flows.CreateProjectFlow
import javax.inject.Inject

/****
 *
 * Created by UY RITHY on 12/19/18.
 *
 */

class ProjectDetailModel @Inject constructor(projectFlow: CreateProjectFlow) : BaseModel() {

    val projectName: String = projectFlow.data.projectName
    val initialTeams = projectFlow.data.initialTeam
}