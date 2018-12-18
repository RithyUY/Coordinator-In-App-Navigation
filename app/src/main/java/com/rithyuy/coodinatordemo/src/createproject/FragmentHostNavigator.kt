package com.rithyuy.coodinatordemo.src.createproject

import androidx.fragment.app.Fragment

interface FragmentHostNavigator {

    fun moveNextOf(fragment: Fragment)

    fun movePreviousOf(fragment: Fragment)
}