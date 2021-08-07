package com.eguerini.veritrancodeexercise.domain.models.result

import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO

data class DepositResult(var client: Client, var newAccountBalance: BalanceVO)
