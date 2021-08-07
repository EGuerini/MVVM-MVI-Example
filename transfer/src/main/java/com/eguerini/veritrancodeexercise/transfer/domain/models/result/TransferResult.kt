package com.eguerini.veritrancodeexercise.transfer.domain.models.result

import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO

data class TransferResult(
    val transferStatus: Boolean,
    val newOriginAccountBalance: BalanceVO
)