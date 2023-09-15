package com.eguerini.veritrancodeexercise.model.state

import com.eguerini.veritrancodeexercise.login.domain.result.LoginResult

sealed class MainState{

    object Idle: MainState()
    object Loading: MainState()
    data class Login(val loginResult: LoginResult) : MainState()
    data class Error(val error: String?): MainState()
}