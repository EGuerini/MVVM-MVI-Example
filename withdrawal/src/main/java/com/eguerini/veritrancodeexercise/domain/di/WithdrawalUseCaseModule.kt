package com.eguerini.veritrancodeexercise.domain.di

import com.eguerini.veritrancodeexercise.data.di.WithdrawalRepositoryModule
import com.eguerini.veritrancodeexercise.domain.interactor.WithdrawalRepository
import com.eguerini.veritrancodeexercise.domain.usecases.WithdrawalUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        WithdrawalRepositoryModule::class
    ]
)
class WithdrawalUseCaseModule {

    @Provides
    @Singleton
    fun withdrawalUseCase(withdrawalRepository: WithdrawalRepository):
            WithdrawalUseCase = WithdrawalUseCase(withdrawalRepository)
}