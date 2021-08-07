package com.eguerini.veritrancodeexercise.login.data.repositories

import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.login.domain.result.LoginResult
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.LoginFailedException

class LoginRepositoryImpl(): LoginRepository {

    override fun login(client: Client, id: String): LoginResult {
        return when{
            id.isNotEmpty() && (id == client.id) -> LoginResult(true, client)
            else -> throw LoginFailedException()
        }
    }
}