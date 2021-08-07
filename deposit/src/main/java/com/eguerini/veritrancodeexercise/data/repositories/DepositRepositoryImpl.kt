package com.eguerini.veritrancodeexercise.data.repositories

import com.eguerini.veritrancodeexercise.domain.interactor.DepositRepository
import com.eguerini.veritrancodeexercise.domain.models.result.DepositResult
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO

class DepositRepositoryImpl: DepositRepository {

    override fun addDeposit(client: Client, amountToOperateVO: AmountToOperateVO):
            DepositResult {
        val newAccountBalance: BalanceVO = client.account.accountBalance
            .plus(amountToOperateVO)
        client.account.accountBalance = newAccountBalance
        return DepositResult(client, newAccountBalance)
    }
}