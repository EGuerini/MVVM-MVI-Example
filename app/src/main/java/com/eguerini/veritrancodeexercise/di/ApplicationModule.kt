package com.eguerini.veritrancodeexercise.di

import android.app.Application
import android.content.Context
import com.eguerini.veritrancodeexercise.app.BaseApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplicationContext(application: BaseApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun provideApplication(application: BaseApplication): Application {
        return application
    }
}