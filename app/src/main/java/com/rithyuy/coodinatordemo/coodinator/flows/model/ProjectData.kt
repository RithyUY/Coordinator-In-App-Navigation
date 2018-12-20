package com.rithyuy.coodinatordemo.coodinator.flows.model

import com.rithyuy.coodinatordemo.model.Team
import javax.inject.Inject
import javax.inject.Singleton

/****
 *
 * Created by UY RITHY on 12/19/18.
 *
 */
@Singleton
class ProjectData @Inject constructor() {

    lateinit var projectName: String

    lateinit var initialTeam: ArrayList<Team>

    var firstDisplayIndex: Int = 0
}