package com.rithyuy.coodinatordemo.src.createproject.teammember

import com.rithyuy.coodinatordemo.base.BaseViewModel
import com.rithyuy.coodinatordemo.model.SelectableTeam
import com.rithyuy.coodinatordemo.model.Team

/****
 *
 * Created by UY RITHY on 12/18/18.
 *
 */
class InviteTeamViewModel : BaseViewModel<InviteTeamModel>() {

    private var mSelectableTeams : ArrayList<SelectableTeam>?=null

    val teams: ArrayList<SelectableTeam> get() {
        if(mSelectableTeams == null) mSelectableTeams = arrayListOf()
        dataModel.teams.forEach { mSelectableTeams!!.add(SelectableTeam(it)) }
        return mSelectableTeams!!
    }

}