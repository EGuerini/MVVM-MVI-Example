package com.eguerini.veritrancodeexercise.domain

import com.eguerini.veritrancodeexercise.domain.interactor.DepositRepository
import com.eguerini.veritrancodeexercise.model.repositories.DepositRepositoryImpl
import com.eguerini.veritrancodeexercise.domain.models.result.DepositResult
import com.eguerini.veritrancodeexercise.domain.exception.NegativeAmountException
import com.eguerini.veritrancodeexercise.domain.usecases.AddDepositUseCase
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
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
class AddDepositUseCaseTest {

    @Mock
    private lateinit var SUT: AddDepositUseCase

    @Mock
    lateinit var account: Account
    @Mock
    lateinit var client: Client
    @Mock
    lateinit var depositRepository: DepositRepository

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        depositRepository = mock(DepositRepositoryImpl::class.java)
        SUT = mock(AddDepositUseCase::class.java)
    }

    //region Iteration 1
    @Test
    fun addDeposit_ok() {
        //Given an existing client with id “francisco” with 100 USD in his account
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        val amountToOperate = AmountToOperateVO(BigDecimal.TEN)

        //When he deposits 10 USD into his account
        lenient().`when`(SUT.executeDeposit(client, amountToOperate))
            //Then the balance of his account is 110 USD
            .thenReturn(DepositResult(client, BalanceVO(BigDecimal(110))))
    }
    //endregion

    //region Iteration 2
    @Test
    fun addDeposit_error_throwNegativeAmountException() {
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        assertThrows<NegativeAmountException> {
            SUT.executeDeposit(client, AmountToOperateVO(BigDecimal(-10)))
        }
    }
    //endregion

}