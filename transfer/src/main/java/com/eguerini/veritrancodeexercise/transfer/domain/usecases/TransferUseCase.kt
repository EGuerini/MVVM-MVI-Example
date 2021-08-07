package com.eguerini.veritrancodeexercise.transfer.domain.usecases

import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.InvalidAddresseeException
import com.eguerini.veritrancodeexercise.domain.exception.NoAccountBalanceException
import com.eguerini.veritrancodeexercise.domain.exception.OverdraftException
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import com.eguerini.veritrancodeexercise.transfer.domain.interactor.TransferRepository
import com.eguerini.veritrancodeexercise.transfer.domain.models.result.TransferResult
import java.math.BigDecimal
import javax.inject.Inject

class TransferUseCase @Inject constructor(
    private val transferRepository: TransferRepository
) {

    @Throws(
        InvalidAddresseeException::class,
        NoAccountBalanceException::class,
        OverdraftException::class)
    fun transfer(
        amountToTransfer: AmountToOperateVO,
        addressee: String,
        originAccount: Account,
        destinyClient: Client
    ): TransferResult {
        validateOriginAccountBalance(amountToTransfer, originAccount)
        validateAddressee(addressee, destinyClient.account)
        return transferRepository.transfer(
            amountToTransfer,
            originAccount,
            destinyClient.account
        )
    }

    private fun validateAddressee(addressee: String, destinyAccount: Account) {
        if(addressee != destinyAccount.alias && addressee != destinyAccount.cbu){
            throw InvalidAddresseeException()
        }
    }

    private fun validateOriginAccountBalance(
        amountToTransfer: AmountToOperateVO,
        originAccount: Account
    ) {
        if(originAccount.accountBalance.compareTo(BalanceVO(BigDecimal.ZERO)) == 0){
            throw NoAccountBalanceException()
        }

        if(originAccount.accountBalance.minus(amountToTransfer) < BalanceVO(BigDecimal.ZERO)) {
            throw OverdraftException()
        }
    }
}