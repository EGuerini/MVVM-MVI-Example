package com.eguerini.veritrancodeexercise.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginUseCase
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.LoginFailedException
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {

    private var _loginResult = MutableLiveData<ClientModel>()
    val loginResult: LiveData<ClientModel> get() = _loginResult

    private var _exception = MutableLiveData<String>()
    val exception: LiveData<String> get() = _exception

    fun doLogin(client: ClientModel, id: String){
        try{
            val balance = BalanceVO(
                client.account.accountBalance.amount
            )
            val account = Account(
                client.account.accountNbr,
                balance,
                client.account.cbu,
                client.account.alias
            )
            val veritranClient = Client(
                client.id,
                client.user,
                client.password,
                client.name,
                client.surname,
                account
            )

            val loginResult = loginUseCase.execute(veritranClient, id)
            if(loginResult.result){
                _loginResult.value = client
            }
        } catch (e: LoginFailedException){
            _exception.value = e.message
        }
    }
}