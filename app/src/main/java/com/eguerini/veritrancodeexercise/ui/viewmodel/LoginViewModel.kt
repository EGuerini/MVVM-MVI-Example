package com.eguerini.veritrancodeexercise.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.LoginFailedException
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.model.state.MainState
import com.eguerini.veritrancodeexercise.intent.LoginIntent
import com.eguerini.veritrancodeexercise.login.domain.result.LoginResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel() {

    val userIntent = Channel<LoginIntent>(Channel.UNLIMITED)
    private var _mainState = MutableStateFlow<MainState>(MainState.Idle)

    val mainState: StateFlow<MainState>
        get() = _mainState

    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it){
                    is LoginIntent.RequestLogin -> doLogin(it)
                }
            }
        }
    }

    private fun doLogin(loginIntent: LoginIntent.RequestLogin){
        val balance = BalanceVO(
            loginIntent.client.account.accountBalance.amount
        )
        val account = Account(
            loginIntent.client.account.accountNbr,
            balance,
            loginIntent.client.account.cbu,
            loginIntent.client.account.alias
        )
        val veritranClient = Client(
            loginIntent.client.id,
            loginIntent.client.user,
            loginIntent.client.password,
            loginIntent.client.name,
            loginIntent.client.surname,
            account
        )

        viewModelScope.launch {
            _mainState.value = MainState.Loading

            _mainState.value = try {
                MainState.Login(loginRepository.login(veritranClient, loginIntent.id))
            } catch (e: LoginFailedException) {
                MainState.Error(e.message)
            }
        }
    }
}