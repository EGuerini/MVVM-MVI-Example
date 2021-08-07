package com.eguerini.veritrancodeexercise.domain.entities

data class Client(
    var id: String,
    var user: String? = "",
    var password: String? = "",
    var name: String? = "",
    var surname: String? = "",
    var account: Account
)