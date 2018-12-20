package com.rithyuy.coodinatordemo.src.projectdetail

import com.rithyuy.coodinatordemo.base.BaseViewModel

/****
 *
 * Created by UY RITHY on 12/19/18.
 *
 */

class ProjectDetailViewModel : BaseViewModel<ProjectDetailModel>() {

    val teams get() = dataModel.initialTeams
    val projectName get() = dataModel.projectName
}