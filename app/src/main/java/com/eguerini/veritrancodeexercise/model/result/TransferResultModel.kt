package com.eguerini.veritrancodeexercise.model.result

import android.os.Parcelable
import com.eguerini.veritrancodeexercise.model.vo.BalanceVOView
import com.eguerini.veritrancodeexercise.model.vo.toModel
import com.eguerini.veritrancodeexercise.transfer.domain.models.result.TransferResult
import kotlinx.parcelize.Parcelize

@Parcelize
class TransferResultModel(val result: Boolean, val newOriginAccountBalance: BalanceVOView):
    Parcelable

fun TransferResult.toModel() = TransferResultModel(
    transferStatus,
    newOriginAccountBalance.toModel()
)