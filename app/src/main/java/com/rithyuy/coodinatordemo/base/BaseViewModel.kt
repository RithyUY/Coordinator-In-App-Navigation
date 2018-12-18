package com.rithyuy.coodinatordemo.base

import androidx.lifecycle.ViewModel
import javax.inject.Inject

abstract class BaseViewModel<M: BaseModel> : ViewModel() {

    @Inject
    lateinit var dataModel: M

    open fun onModelInit() {}
}