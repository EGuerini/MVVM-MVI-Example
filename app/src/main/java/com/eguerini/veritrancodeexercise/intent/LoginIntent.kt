package com.eguerini.veritrancodeexercise.intent

import com.eguerini.veritrancodeexercise.model.entities.ClientModel

sealed class LoginIntent {

    data class RequestLogin(val client: ClientModel, val id: String): LoginIntent()
}