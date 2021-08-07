package com.eguerini.veritrancodeexercise.transfer.domain.interactor

import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.transfer.domain.models.result.TransferResult

interface TransferRepository {

    fun transfer(amountToTransfer: AmountToOperateVO,
                 originAccount: Account,
                 destinyAccount: Account): TransferResult
}