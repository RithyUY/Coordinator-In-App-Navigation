package com.rithyuy.coodinatordemo.src.main

import com.rithyuy.coodinatordemo.base.BaseViewModel
import com.rithyuy.coodinatordemo.coodinator.flows.SignUpFlow
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class MainViewModel : BaseViewModel<MainModel>() {

    val usernameField: Subject<String> = PublishSubject.create()

    override fun onModelInit() {
        usernameField.onNext(dataModel.userName)
    }
}