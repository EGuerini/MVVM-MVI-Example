package com.eguerini.veritrancodeexercise.domain

import com.eguerini.veritrancodeexercise.data.repositories.DepositRepositoryImpl
import com.eguerini.veritrancodeexercise.domain.models.result.DepositResult
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
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
class DepositRepositoryTest {

    @Mock
    lateinit var SUT: DepositRepositoryImpl

    @Mock
    lateinit var client: Client
    @Mock
    lateinit var account: Account

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        SUT = mock(DepositRepositoryImpl::class.java)
    }

    @Test
    fun addDeposit_ok(){
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        val amountToOperate = AmountToOperateVO(BigDecimal.TEN)

        //When he deposits 10 USD into his account
        lenient().`when`(SUT.addDeposit(client, amountToOperate))
            //Then the balance of his account is 110 USD
            .thenReturn(DepositResult(client, BalanceVO(BigDecimal(110))))
    }
}