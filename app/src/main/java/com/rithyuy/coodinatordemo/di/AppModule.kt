package com.rithyuy.coodinatordemo.di

import com.rithyuy.coodinatordemo.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun providesApp() : App = app
}