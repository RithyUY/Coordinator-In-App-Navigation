package com.rithyuy.coodinatordemo.coodinator.flows.model

import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpData @Inject constructor() {

    val onDataChange by lazy { PublishSubject.create<SignUpData>() }
    var username: String = ""
    var imageUrl: String = ""
    var email: String = ""
}