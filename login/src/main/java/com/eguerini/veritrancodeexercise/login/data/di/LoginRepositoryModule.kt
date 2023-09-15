package com.eguerini.veritrancodeexercise.login.data.di

import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.login.model.repositories.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginRepositoryModule {

    @Provides
    @Singleton
    fun loginRepository(): LoginRepository = LoginRepositoryImpl()
}