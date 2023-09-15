package com.eguerini.veritrancodeexercise.data.di

import com.eguerini.veritrancodeexercise.domain.interactor.WithdrawalRepository
import com.eguerini.veritrancodeexercise.model.repositories.WithdrawalRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WithdrawalRepositoryModule {

    @Provides
    @Singleton
    fun withdrawalRepository(): WithdrawalRepository = WithdrawalRepositoryImpl()
}