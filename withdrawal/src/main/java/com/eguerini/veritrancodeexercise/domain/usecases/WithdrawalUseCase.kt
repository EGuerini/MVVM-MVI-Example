package com.eguerini.veritrancodeexercise.domain.usecases

import com.eguerini.veritrancodeexercise.domain.interactor.WithdrawalRepository
import com.eguerini.veritrancodeexercise.domain.models.result.WithdrawalResult
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.NoAccountBalanceException
import com.eguerini.veritrancodeexercise.domain.exception.OverdraftException
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import java.math.BigDecimal
import javax.inject.Inject

class WithdrawalUseCase @Inject constructor(
    private val withdrawalRepository: WithdrawalRepository
) {

    @Throws(OverdraftException::class, NoAccountBalanceException::class)
    fun executeWithdraw(
        client: Client,
        amountToOperateVO: AmountToOperateVO
    ): WithdrawalResult {
        validateAccountBalance(client.account, amountToOperateVO)

        return withdrawalRepository.withdrawal(
            client,
            amountToOperateVO
        )
    }

    private fun validateAccountBalance(account: Account, amountToOperateVO: AmountToOperateVO) {
        if(account.accountBalance.compareTo(BalanceVO(BigDecimal.ZERO)) == 0){
            throw NoAccountBalanceException()
        }

        if(account.accountBalance.minus(amountToOperateVO) < BalanceVO(BigDecimal.ZERO)) {
            throw OverdraftException()
        }
    }
}