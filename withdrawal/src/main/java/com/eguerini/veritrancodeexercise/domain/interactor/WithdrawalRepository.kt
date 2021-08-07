package com.eguerini.veritrancodeexercise.domain.interactor

import com.eguerini.veritrancodeexercise.domain.models.result.WithdrawalResult
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO

interface WithdrawalRepository {

    fun withdrawal(client: Client, amountToOperateVO: AmountToOperateVO): WithdrawalResult

}