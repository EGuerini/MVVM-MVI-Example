package com.eguerini.veritrancodeexercise.data.repositories

import com.eguerini.veritrancodeexercise.domain.interactor.WithdrawalRepository
import com.eguerini.veritrancodeexercise.domain.models.result.WithdrawalResult
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO

class WithdrawalRepositoryImpl: WithdrawalRepository {

    override fun withdrawal(client: Client, amountToOperateVO: AmountToOperateVO): WithdrawalResult {
        val newAccountBalance: BalanceVO = client.account.accountBalance.minus(amountToOperateVO)
        client.account.accountBalance = newAccountBalance
        return WithdrawalResult(client, newAccountBalance)
    }
}