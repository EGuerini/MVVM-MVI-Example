package com.eguerini.veritrancodeexercise.transfer

import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.InvalidAddresseeException
import com.eguerini.veritrancodeexercise.domain.exception.NoAccountBalanceException
import com.eguerini.veritrancodeexercise.domain.exception.OverdraftException
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import com.eguerini.veritrancodeexercise.transfer.domain.interactor.TransferRepository
import com.eguerini.veritrancodeexercise.transfer.model.repositories.TransferRepositoryImpl
import com.eguerini.veritrancodeexercise.transfer.domain.models.result.TransferResult
import com.eguerini.veritrancodeexercise.transfer.domain.usecases.TransferUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class TransferUseCaseTest {

    @Mock
    private lateinit var SUT: TransferUseCase

    @Mock
    lateinit var franciscoClient: Client
    @Mock
    lateinit var emanuelClient: Client
    @Mock
    lateinit var franciscoAccount: Account
    @Mock
    lateinit var emanuelAccount: Account

    @Mock
    lateinit var transferRepository: TransferRepository


    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        transferRepository = mock(TransferRepositoryImpl::class.java)
        SUT = mock(TransferUseCase::class.java)
    }

    //region Iteration 7
    @Test
    fun transfer_ok(){
        franciscoAccount = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        franciscoClient = Client(
            id = "francisco",
            account = franciscoAccount
        )
        emanuelAccount = Account(
            accountBalance = BalanceVO(BigDecimal(50)),
            cbu = "0151413121110987654321",
            alias = "ema.guerini"
        )
        emanuelClient = Client(
            id = "emanuel",
            account = emanuelAccount
        )

        val destinatario = "ema.guerini"
        val amountToTransfer = AmountToOperateVO(BigDecimal.TEN)

        lenient().`when`(SUT.transfer(
            amountToTransfer,
            destinatario,
            franciscoAccount,
            emanuelClient))
            .thenReturn(TransferResult(true, BalanceVO(BigDecimal(90))))
    }

    @Test
    fun transfer_error_throwInvalidAddresseeException(){
        franciscoAccount = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        franciscoClient = Client(
            id = "francisco",
            account = franciscoAccount
        )
        emanuelAccount = Account(
            accountBalance = BalanceVO(BigDecimal(50)),
            cbu = "0151413121110987654321",
            alias = "ema.guerini"
        )
        emanuelClient = Client(
            id = "emanuel",
            account = emanuelAccount
        )

        val amountToTransfer = AmountToOperateVO(BigDecimal.TEN)
        val destinatario = "mock"

        lenient().`when`( SUT.transfer(
            amountToTransfer,
            destinatario,
            franciscoAccount,
            emanuelClient))
            .thenThrow(InvalidAddresseeException::class.java)
    }

    @Test
    fun transfer_error_throwNoAccountBalanceException(){
        franciscoAccount = Account(
            accountBalance = BalanceVO(BigDecimal.ZERO),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        franciscoClient = Client(
            id = "francisco",
            account = franciscoAccount
        )
        emanuelAccount = Account(
            accountBalance = BalanceVO(BigDecimal(50)),
            cbu = "0151413121110987654321",
            alias = "ema.guerini"
        )
        emanuelClient = Client(
            id = "emanuel",
            account = emanuelAccount
        )

        val destinatario = "ema.guerini"
        val amountToTransfer = AmountToOperateVO(BigDecimal.TEN)
        lenient().`when`( SUT.transfer(
            amountToTransfer,
            destinatario,
            franciscoAccount,
            emanuelClient))
            .thenThrow(NoAccountBalanceException::class.java)
    }

    @Test
    fun transfer_error_throwOverdraftException(){
        franciscoAccount = Account(
            accountBalance = BalanceVO(BigDecimal(9)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        franciscoClient = Client(
            id = "francisco",
            account = franciscoAccount
        )
        emanuelAccount = Account(
            accountBalance = BalanceVO(BigDecimal(50)),
            cbu = "0151413121110987654321",
            alias = "ema.guerini"
        )
        emanuelClient = Client(
            id = "emanuel",
            account = emanuelAccount
        )

        val destinatario = "ema.guerini"
        val amountToTransfer = AmountToOperateVO(BigDecimal.TEN)
        lenient().`when`( SUT.transfer(
            amountToTransfer,
            destinatario,
            franciscoAccount,
            emanuelClient))
            .thenThrow(OverdraftException::class.java)
    }
    //endregion
}