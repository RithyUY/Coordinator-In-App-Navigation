package com.rithyuy.coodinatordemo.di

import com.rithyuy.coodinatordemo.App
import com.rithyuy.coodinatordemo.src.createproject.projectname.ProjectNameViewModel
import com.rithyuy.coodinatordemo.src.main.MainViewModel
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
}