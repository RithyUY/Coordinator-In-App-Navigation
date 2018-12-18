package com.rithyuy.coodinatordemo

import android.app.Application
import com.rithyuy.coodinatordemo.coodinator.AppFlow
import com.rithyuy.coodinatordemo.coodinator.Navigator
import com.rithyuy.coodinatordemo.di.AppComponent
import com.rithyuy.coodinatordemo.di.AppModule
import com.rithyuy.coodinatordemo.di.DaggerAppComponent
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var appFlow: AppFlow

    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().appModule(AppModule(this)).build() }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}