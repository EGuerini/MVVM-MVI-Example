package com.eguerini.veritrancodeexercise.model.result

import android.os.Parcelable
import com.eguerini.veritrancodeexercise.domain.models.result.DepositResult
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.model.entities.toModel
import com.eguerini.veritrancodeexercise.model.vo.BalanceVOView
import com.eguerini.veritrancodeexercise.model.vo.toModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepositResultModel(val client: ClientModel, val newAccountBalance: BalanceVOView):
    Parcelable

fun DepositResult.toModel() = DepositResultModel(
    client.toModel(),
    newAccountBalance.toModel()
)