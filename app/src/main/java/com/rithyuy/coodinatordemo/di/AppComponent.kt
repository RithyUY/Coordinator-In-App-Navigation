package com.rithyuy.coodinatordemo.di

import com.rithyuy.coodinatordemo.App
import com.rithyuy.coodinatordemo.src.createproject.CreateProjectActivity
import com.rithyuy.coodinatordemo.src.createproject.launcproject.LaunchProjectViewModel
import com.rithyuy.coodinatordemo.src.createproject.projectname.ProjectNameViewModel
import com.rithyuy.coodinatordemo.src.createproject.teammember.InviteTeamViewModel
import com.rithyuy.coodinatordemo.src.main.MainViewModel
import com.rithyuy.coodinatordemo.src.projectdetail.ProjectDetailViewModel
import com.rithyuy.coodinatordemo.src.signup.SignUpActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)

    fun plus(mainVM: MainViewModel)

    fun plus(signUpActivity: SignUpActivity)

    fun plus(projectNameVM: ProjectNameViewModel)

    fun plus(inviteTeamViewModel: InviteTeamViewModel)

    fun plus(projectDetailVM: ProjectDetailViewModel)

    fun plus(launchProjectViewModel: LaunchProjectViewModel)

    fun plus(projectHost: CreateProjectActivity)
}