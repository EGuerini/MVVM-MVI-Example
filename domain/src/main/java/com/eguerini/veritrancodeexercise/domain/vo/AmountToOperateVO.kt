package com.eguerini.veritrancodeexercise.domain.vo

import com.eguerini.veritrancodeexercise.domain.exception.NegativeAmountException
import java.math.BigDecimal

data class AmountToOperateVO (var amount: BigDecimal): Comparable<AmountToOperateVO>{

    init {
        require(amount >= BigDecimal.ZERO){
            throw NegativeAmountException()
        }
    }

    override fun compareTo(other: AmountToOperateVO): Int = this.amount.compareTo(other.amount)
}