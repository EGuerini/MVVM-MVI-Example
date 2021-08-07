package com.eguerini.veritrancodeexercise.model.entities

import android.os.Parcelable
import com.eguerini.veritrancodeexercise.domain.entities.Client
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class ClientModel @Inject constructor(
    var id: String,
    var user: String? = "",
    var password: String? = "",
    var name: String? = "",
    var surname: String? = "",
    var account: AccountModel
): Parcelable

fun Client.toModel() = ClientModel(
    id,
    user,
    password,
    name,
    surname,
    account.toModel()
)