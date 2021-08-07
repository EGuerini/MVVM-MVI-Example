package com.eguerini.veritrancodeexercise.login.domain.result

import com.eguerini.veritrancodeexercise.domain.entities.Client

data class LoginResult(val result: Boolean, val client: Client)