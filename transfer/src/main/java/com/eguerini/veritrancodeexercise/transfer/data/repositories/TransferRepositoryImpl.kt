package com.eguerini.veritrancodeexercise.transfer.data.repositories

import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import com.eguerini.veritrancodeexercise.transfer.domain.interactor.TransferRepository
import com.eguerini.veritrancodeexercise.transfer.domain.models.result.TransferResult

class TransferRepositoryImpl: TransferRepository {

    override fun transfer(
        amountToTransfer: AmountToOperateVO,
        originAccount: Account,
        destinyAccount: Account
    ): TransferResult {
        val newDestinyAccountBalance: BalanceVO =
            destinyAccount.accountBalance.plus(amountToTransfer)
        destinyAccount.accountBalance = newDestinyAccountBalance
        val newOriginAccountBalance: BalanceVO = originAccount.accountBalance.minus(amountToTransfer)
        originAccount.accountBalance = newOriginAccountBalance
        return TransferResult(true, newOriginAccountBalance)
    }
}