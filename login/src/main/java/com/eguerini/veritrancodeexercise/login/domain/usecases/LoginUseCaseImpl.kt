package com.eguerini.veritrancodeexercise.login.domain.usecases

import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginUseCase
import com.eguerini.veritrancodeexercise.login.domain.result.LoginResult
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.LoginFailedException
import javax.inject.Inject
import kotlin.jvm.Throws

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
): LoginUseCase {

    @Throws(LoginFailedException::class)
    override fun execute(client:Client, id: String): LoginResult {
        return loginRepository.login(client, id)
    }
}