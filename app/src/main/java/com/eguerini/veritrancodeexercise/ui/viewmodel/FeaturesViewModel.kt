package com.eguerini.veritrancodeexercise.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eguerini.veritrancodeexercise.domain.usecases.AddDepositUseCase
import com.eguerini.veritrancodeexercise.domain.usecases.WithdrawalUseCase
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.model.result.DepositResultModel
import com.eguerini.veritrancodeexercise.model.result.TransferResultModel
import com.eguerini.veritrancodeexercise.model.result.WithdrawalResultModel
import com.eguerini.veritrancodeexercise.model.result.toModel
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import com.eguerini.veritrancodeexercise.transfer.domain.usecases.TransferUseCase
import java.math.BigDecimal
import javax.inject.Inject

class FeaturesViewModel @Inject constructor(
    private val withdrawalUseCase: WithdrawalUseCase,
    private val depositUseCase: AddDepositUseCase,
    private val transferUseCase: TransferUseCase): ViewModel() {

    private var _depositResult = MutableLiveData<DepositResultModel>()
    val depositResult: LiveData<DepositResultModel> get() = _depositResult

    private var _withdrawalResult = MutableLiveData<WithdrawalResultModel>()
    val withdrawalResult: LiveData<WithdrawalResultModel> get() = _withdrawalResult

    private var _transferResult = MutableLiveData<TransferResultModel>()
    val transferResult: LiveData<TransferResultModel> get() = _transferResult

    private var _exception = MutableLiveData<String>()
    val exception: LiveData<String> get() = _exception

    fun doDeposit(client: ClientModel, amountToOperate: String) {
        try {
            val balance = BalanceVO(
                client.account.accountBalance.amount
            )
            val account = Account(
                client.account.accountNbr,
                balance,
                client.account.cbu,
                client.account.alias
            )
            val veritranClient = Client(
                client.id,
                client.user,
                client.password,
                client.name,
                client.surname,
                account
            )

            val depositResult = depositUseCase.executeDeposit(
                veritranClient,
                AmountToOperateVO(BigDecimal(amountToOperate))
            )
            _depositResult.value = depositResult.toModel()
        } catch (e: Exception){
            _exception.value = e.message
        }
    }

    fun doWithdrawal(client: ClientModel, amountToOperate: String) {
        try {
            val balance = BalanceVO(
                client.account.accountBalance.amount
            )
            val account = Account(
                client.account.accountNbr,
                balance,
                client.account.cbu,
                client.account.alias
            )
            val veritranClient = Client(
                client.id,
                client.user,
                client.password,
                client.name,
                client.surname,
                account
            )

            val withdrawalResult = withdrawalUseCase.executeWithdraw(
                veritranClient,
                AmountToOperateVO(BigDecimal(amountToOperate))
            )
            _withdrawalResult.value = withdrawalResult.toModel()
        } catch (e: Exception){
            _exception.value = e.message
        }
    }

    fun doTransfer(amountToTransfer: String,
                   addressee: String,
                   clientModel: ClientModel,
                   clientDestinyModel: ClientModel){
        try {
            val balance = BalanceVO(clientModel.account.accountBalance.amount)
            val originAccount = Account(
                clientModel.account.accountNbr,
                balance,
                clientModel.account.cbu,
                clientModel.account.alias
            )

            val destinyAccount = Account(
                clientDestinyModel.account.accountNbr,
                balance,
                clientDestinyModel.account.cbu,
                clientDestinyModel.account.alias
            )

            val clientDestiny = Client(
                clientDestinyModel.id,
                clientDestinyModel.user,
                clientDestinyModel.password,
                clientDestinyModel.name,
                clientDestinyModel.surname,
                destinyAccount,
            )

            val transferResult = transferUseCase.transfer(
                AmountToOperateVO(BigDecimal(amountToTransfer)),
                addressee,
                originAccount,
                clientDestiny
            )
            _transferResult.value = transferResult.toModel()
        } catch (e: Exception){
            _exception.value = e.message
        }
    }
}