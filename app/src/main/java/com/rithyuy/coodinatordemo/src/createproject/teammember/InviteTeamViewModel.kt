package com.rithyuy.coodinatordemo.src.createproject.teammember

import com.rithyuy.coodinatordemo.base.BaseViewModel
import com.rithyuy.coodinatordemo.coodinator.flows.CreateProjectFlow
import com.rithyuy.coodinatordemo.model.SelectableTeam
import com.rithyuy.coodinatordemo.model.Team
import javax.inject.Inject

/****
 *
 * Created by UY RITHY on 12/18/18.
 *
 */
class InviteTeamViewModel : BaseViewModel<InviteTeamModel>() {

    @Inject
    lateinit var createProjectFlow: CreateProjectFlow

    val onProjectNameChange get() = createProjectFlow.onProjectNameChange

    private var mSelectableTeams : ArrayList<SelectableTeam>?=null

    val teams: ArrayList<SelectableTeam> get() {
        if(mSelectableTeams == null) mSelectableTeams = arrayListOf()
        dataModel.teams.forEach { mSelectableTeams!!.add(SelectableTeam(it)) }
        return mSelectableTeams!!
    }

    fun submitInitialTeam() {
        val teams = arrayListOf<Team>()
        this.teams.filter { it.isSelect }.forEach { item-> teams.add(item.team) }
        createProjectFlow.submitInitialTeams(teams)
    }

}