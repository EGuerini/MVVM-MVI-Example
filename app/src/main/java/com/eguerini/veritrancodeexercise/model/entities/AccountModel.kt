package com.eguerini.veritrancodeexercise.model.entities

import android.os.Parcelable
import com.eguerini.veritrancodeexercise.model.vo.BalanceVOView
import com.eguerini.veritrancodeexercise.model.vo.toModel
import com.eguerini.veritrancodeexercise.domain.entities.Account
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class AccountModel @Inject constructor (
    var accountNbr: String? = "",
    var accountBalance: BalanceVOView,
    var cbu: String,
    var alias: String,
): Parcelable

fun Account.toModel() = AccountModel(
    accountNbr,
    accountBalance.toModel(),
    cbu,
    alias
)