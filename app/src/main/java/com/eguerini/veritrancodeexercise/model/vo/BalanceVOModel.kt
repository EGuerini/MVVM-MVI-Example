package com.eguerini.veritrancodeexercise.model.vo

import android.os.Parcelable
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class BalanceVOView(var amount: BigDecimal): Parcelable

fun BalanceVO.toModel() = BalanceVOView(amount)