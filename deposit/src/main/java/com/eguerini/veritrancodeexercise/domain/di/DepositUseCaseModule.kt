package com.eguerini.veritrancodeexercise.domain.di

import com.eguerini.veritrancodeexercise.data.di.DepositRepositoryModule
import com.eguerini.veritrancodeexercise.domain.interactor.DepositRepository
import com.eguerini.veritrancodeexercise.domain.usecases.AddDepositUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DepositRepositoryModule::class
    ]
)
class DepositUseCaseModule {

    @Provides
    @Singleton
    fun addDepositUseCase(depositRepository: DepositRepository
    ): AddDepositUseCase =
        AddDepositUseCase(depositRepository)
}