package com.eguerini.veritrancodeexercise.login.domain.di

import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginUseCase
import com.eguerini.veritrancodeexercise.login.domain.usecases.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginUseCaseModule {

    @Provides
    @Singleton
    fun loginUseCase(loginRepository: LoginRepository): LoginUseCase =
        LoginUseCaseImpl(loginRepository)
}