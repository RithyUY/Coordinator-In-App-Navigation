package com.rithyuy.coodinatordemo.coodinator.flows

import com.rithyuy.coodinatordemo.coodinator.Navigator
import com.rithyuy.coodinatordemo.coodinator.flows.model.ProjectData
import com.rithyuy.coodinatordemo.model.Team
import com.rithyuy.coodinatordemo.src.projectdetail.displayteam.DisplayTeamActivity
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

/****
 *
 * Created by UY RITHY on 12/19/18.
 *
 */

@Singleton
class CreateProjectFlow @Inject constructor(private val navigator: Navigator) {

    @Inject
    lateinit var data: ProjectData

    val onProjectNameChange = PublishSubject.create<String>()

    fun submitProjectName(projectName: String) {
        data.projectName = projectName
        onProjectNameChange.onNext(projectName)
    }

    fun submitInitialTeams(teams: ArrayList<Team>) {
        data.initialTeam = teams
    }

    fun displayTeamAt(index: Int) {
        data.firstDisplayIndex = index
        navigator.navigate(DisplayTeamActivity::class.java)
    }
}