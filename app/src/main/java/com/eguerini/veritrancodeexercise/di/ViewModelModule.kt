package com.eguerini.veritrancodeexercise.di

import com.eguerini.veritrancodeexercise.domain.di.DepositUseCaseModule
import com.eguerini.veritrancodeexercise.domain.di.WithdrawalUseCaseModule
import com.eguerini.veritrancodeexercise.domain.usecases.AddDepositUseCase
import com.eguerini.veritrancodeexercise.domain.usecases.WithdrawalUseCase
import com.eguerini.veritrancodeexercise.login.data.di.LoginRepositoryModule
import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.transfer.domain.di.TransferUseCaseModule
import com.eguerini.veritrancodeexercise.transfer.domain.usecases.TransferUseCase
import com.eguerini.veritrancodeexercise.ui.viewmodel.factory.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        LoginRepositoryModule::class,
        WithdrawalUseCaseModule::class,
        DepositUseCaseModule::class,
        TransferUseCaseModule::class
    ]
)
class ViewModelModule {

    @Provides
    fun viewModelFactory(loginRepository: LoginRepository,
                         withdrawalUseCase: WithdrawalUseCase,
                         addDepositUseCase: AddDepositUseCase,
                         transferUseCase: TransferUseCase
    ): ViewModelFactory =
        ViewModelFactory(loginRepository, withdrawalUseCase, addDepositUseCase, transferUseCase)
}