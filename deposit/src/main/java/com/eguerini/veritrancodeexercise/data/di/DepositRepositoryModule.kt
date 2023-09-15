package com.eguerini.veritrancodeexercise.data.di

import com.eguerini.veritrancodeexercise.domain.interactor.DepositRepository
import com.eguerini.veritrancodeexercise.model.repositories.DepositRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DepositRepositoryModule {

    @Provides
    @Singleton
    fun veritranRepository(): DepositRepository = DepositRepositoryImpl()
}