package com.eguerini.veritrancodeexercise.domain.entities

import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO

data class Account (
    var accountNbr: String? = "",
    var accountBalance: BalanceVO,
    var cbu: String,
    var alias: String,
)