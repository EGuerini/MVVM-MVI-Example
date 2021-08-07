package com.eguerini.veritrancodeexercise.data

import com.eguerini.veritrancodeexercise.domain.interactor.WithdrawalRepository
import com.eguerini.veritrancodeexercise.data.repositories.WithdrawalRepositoryImpl
import com.eguerini.veritrancodeexercise.domain.models.result.WithdrawalResult
import com.eguerini.veritrancodeexercise.domain.usecases.WithdrawalUseCase
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.NegativeAmountException
import com.eguerini.veritrancodeexercise.domain.exception.NoAccountBalanceException
import com.eguerini.veritrancodeexercise.domain.exception.OverdraftException
import com.eguerini.veritrancodeexercise.domain.vo.AmountToOperateVO
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class WithdrawalUseCaseTest {

    @Mock
    private lateinit var SUT: WithdrawalUseCase

    @Mock
    lateinit var client: Client
    @Mock
    lateinit var account: Account
    @Mock
    lateinit var withdrawalRepository: WithdrawalRepository

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        withdrawalRepository = mock(WithdrawalRepositoryImpl::class.java)
        SUT = mock(WithdrawalUseCase::class.java)
    }

    //region Iteration 3
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
        lenient().`when`(SUT.executeWithdraw(client, AmountToOperateVO(BigDecimal.TEN)))
            //Then the new balance is 90 USD
            .thenReturn(WithdrawalResult(client, BalanceVO(BigDecimal(90))))
    }

    @Test
    fun withdrawal_error_throwNoAccountBalanceException(){
        account = Account(
            accountBalance = BalanceVO(BigDecimal.ZERO),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        lenient().`when`(SUT.executeWithdraw(client, AmountToOperateVO(BigDecimal.TEN)))
            .thenThrow(NoAccountBalanceException::class.java)
    }
    //endregion

    //region Iteration 4
    @Test
    fun withdrawal_error_throwOverdraftException(){
        account = Account(
            accountBalance = BalanceVO(BigDecimal(9)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        lenient().`when`(SUT.executeWithdraw(client, AmountToOperateVO(BigDecimal.TEN)))
            .thenThrow(OverdraftException::class.java)
    }
    //endregion

    //region Iteration 5
    @Test
    fun withdrawal_error_throwNegativeAmountException(){
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        assertThrows<NegativeAmountException> {
            SUT.executeWithdraw(client, AmountToOperateVO(BigDecimal(-10)))
        }
    }
    //endregion
}