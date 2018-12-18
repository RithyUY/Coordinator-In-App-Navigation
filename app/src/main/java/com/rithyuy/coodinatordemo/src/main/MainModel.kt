package com.rithyuy.coodinatordemo.src.main

import com.rithyuy.coodinatordemo.base.BaseModel
import javax.inject.Inject

class MainModel  @Inject constructor() : BaseModel() {
    val userName: String = "Default values"
}