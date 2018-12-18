package com.rithyuy.coodinatordemo.src.createproject.teammember

import com.rithyuy.coodinatordemo.base.BaseModel
import com.rithyuy.coodinatordemo.base.BaseView
import com.rithyuy.coodinatordemo.model.Repositories
import com.rithyuy.coodinatordemo.model.Team
import javax.inject.Inject

/****
 *
 * Created by UY RITHY on 12/18/18.
 *
 */
class InviteTeamModel @Inject constructor(private val repositories: Repositories) : BaseModel() {

    val teams: ArrayList<Team> get() = repositories.teams
}