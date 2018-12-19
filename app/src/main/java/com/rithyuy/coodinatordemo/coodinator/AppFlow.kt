package com.rithyuy.coodinatordemo.coodinator

import com.rithyuy.coodinatordemo.coodinator.flows.CreateProjectFlow
import com.rithyuy.coodinatordemo.coodinator.flows.SignUpFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppFlow @Inject constructor() {

    @Inject
    lateinit var signUpFlow: SignUpFlow

    @Inject
    lateinit var createProject: CreateProjectFlow

}