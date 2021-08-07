package com.eguerini.veritrancodeexercise.login.domain.interactor

import com.eguerini.veritrancodeexercise.login.domain.result.LoginResult
import com.eguerini.veritrancodeexercise.domain.entities.Client

interface LoginUseCase {

    fun execute(client: Client, id: String): LoginResult
}