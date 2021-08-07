package com.eguerini.veritrancodeexercise.domain.vo

import java.math.BigDecimal

data class BalanceVO(var amount: BigDecimal): Comparable<BalanceVO> {

    override fun compareTo(other: BalanceVO): Int = this.amount.compareTo(other.amount)

    operator fun plus(amountToOperateVO: AmountToOperateVO) =
        BalanceVO(this.amount + amountToOperateVO.amount)
    operator fun minus(amountToOperateVO: AmountToOperateVO) =
        BalanceVO(this.amount - amountToOperateVO.amount)
}
