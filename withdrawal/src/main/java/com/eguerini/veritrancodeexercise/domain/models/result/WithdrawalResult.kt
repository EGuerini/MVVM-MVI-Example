package com.eguerini.veritrancodeexercise.domain.models.result

import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO

data class WithdrawalResult(val client: Client, val newAccountBalance: BalanceVO)