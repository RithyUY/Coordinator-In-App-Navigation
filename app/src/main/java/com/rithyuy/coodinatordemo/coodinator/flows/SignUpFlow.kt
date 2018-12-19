package com.rithyuy.coodinatordemo.coodinator.flows

import com.rithyuy.coodinatordemo.coodinator.Navigator
import com.rithyuy.coodinatordemo.coodinator.flows.model.SignUpData
import com.rithyuy.coodinatordemo.src.signup.SignUpActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpFlow  @Inject constructor(private val navigator: Navigator) {

    @Inject
    lateinit var signUpData: SignUpData

    fun submitUsername(values: String) {
        signUpData.username = values
        signUpData.onDataChange.onNext(signUpData)
        navigator.navigate(SignUpActivity::class.java)
    }

    fun submitImageUrl(values: String) {
        signUpData.imageUrl =values
        navigator.navigate(SignUpActivity::class.java)
    }
}