package com.eguerini.veritrancodeexercise.data

import com.eguerini.veritrancodeexercise.model.repositories.WithdrawalRepositoryImpl
import com.eguerini.veritrancodeexercise.domain.models.result.WithdrawalResult
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
class WithdrawalRepositoryTest {

    @Mock
    lateinit var SUT: WithdrawalRepositoryImpl
    @Mock
    lateinit var client: Client
    @Mock
    lateinit var account: Account

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        SUT = mock(WithdrawalRepositoryImpl::class.java)
    }

    @Test
    fun withdrawal_ok(){
        //Given an existing client with id “francisco” with 100 USD in his account
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        //When he withdraws 10 USD from his account
        lenient().`when`(SUT.withdrawal(client, AmountToOperateVO(BigDecimal.TEN)))
            //Then the new balance is 90 USD
            .thenReturn(WithdrawalResult(client, BalanceVO(BigDecimal(90))))
    }
}