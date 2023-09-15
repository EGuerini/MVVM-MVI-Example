package com.eguerini.veritrancodeexercise.transfer

import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import com.eguerini.veritrancodeexercise.transfer.model.repositories.TransferRepositoryImpl
import com.eguerini.veritrancodeexercise.transfer.domain.models.result.TransferResult
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class TransferRepositoryTest {

    @Mock
    lateinit var SUT: TransferRepositoryImpl
    @Mock
    lateinit var franciscoClient: Client
    @Mock
    lateinit var emanuelClient: Client
    @Mock
    lateinit var franciscoAccount: Account
    @Mock
    lateinit var emanuelAccount: Account

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        SUT = mock(TransferRepositoryImpl::class.java)
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

        val amountToTransfer = AmountToOperateVO(BigDecimal.TEN)
        lenient().`when`(SUT.transfer(
            amountToTransfer,
            franciscoAccount,
            emanuelAccount))
            .thenReturn(TransferResult(true, BalanceVO(BigDecimal(90))))
    }
    //endregion
}