package com.eguerini.veritrancodeexercise.app

import android.app.Application
import com.eguerini.veritrancodeexercise.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        DaggerApplicationComponent.builder()
            .applicationBind(this)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}