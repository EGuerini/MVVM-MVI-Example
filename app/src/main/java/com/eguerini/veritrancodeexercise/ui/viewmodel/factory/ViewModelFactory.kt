package com.eguerini.veritrancodeexercise.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eguerini.veritrancodeexercise.domain.usecases.AddDepositUseCase
import com.eguerini.veritrancodeexercise.domain.usecases.WithdrawalUseCase
import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginUseCase
import com.eguerini.veritrancodeexercise.transfer.domain.usecases.TransferUseCase
import com.eguerini.veritrancodeexercise.ui.viewmodel.FeaturesViewModel
import com.eguerini.veritrancodeexercise.ui.viewmodel.LoginViewModel
import java.lang.RuntimeException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val loginUseCase: LoginUseCase,
    private val withdrawalUseCase: WithdrawalUseCase,
    private val addDepositUseCase: AddDepositUseCase,
    private val transferUseCase: TransferUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> {
                LoginViewModel(loginUseCase) as T
            }
            FeaturesViewModel::class.java -> {
                FeaturesViewModel(withdrawalUseCase, addDepositUseCase, transferUseCase) as T
            }
            else -> {
                throw RuntimeException("View model invalid: $modelClass")
            }
        }
    }

}