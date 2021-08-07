package com.eguerini.veritrancodeexercise.domain.interactor

import com.eguerini.veritrancodeexercise.domain.models.result.DepositResult
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO

interface DepositRepository {

    fun addDeposit(client: Client, amountToOperateVO: AmountToOperateVO): DepositResult

}