package com.eguerini.veritrancodeexercise.domain.usecases

import com.eguerini.veritrancodeexercise.domain.interactor.DepositRepository
import com.eguerini.veritrancodeexercise.domain.models.result.DepositResult
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import javax.inject.Inject

class AddDepositUseCase @Inject constructor(
    private val depositRepository: DepositRepository
) {

    fun executeDeposit(
        client: Client,
        amountToOperateVO: AmountToOperateVO
    ): DepositResult {
        return depositRepository.addDeposit(
            client,
            amountToOperateVO
        )
    }
}