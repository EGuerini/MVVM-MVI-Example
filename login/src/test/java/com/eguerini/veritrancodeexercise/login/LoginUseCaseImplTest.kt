package com.eguerini.veritrancodeexercise.login

import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.login.data.repositories.LoginRepositoryImpl
import com.eguerini.veritrancodeexercise.login.domain.result.LoginResult
import com.eguerini.veritrancodeexercise.login.domain.usecases.LoginUseCaseImpl
import com.eguerini.veritrancodeexercise.domain.entities.Account
import com.eguerini.veritrancodeexercise.domain.entities.Client
import com.eguerini.veritrancodeexercise.domain.exception.LoginFailedException
import com.eguerini.veritrancodeexercise.domain.vo.BalanceVO
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseImplTest {

    @Mock
    private lateinit var SUT: LoginUseCaseImpl

    @Mock
    lateinit var account: Account
    @Mock
    lateinit var client: Client
    @Mock
    lateinit var loginRepository: LoginRepository

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        loginRepository = mock(LoginRepositoryImpl::class.java)
        SUT = mock(LoginUseCaseImpl::class.java)
    }

    @Test
    fun login_ok(){
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        lenient().`when`(SUT.execute(client, "francisco"))
            .thenReturn(LoginResult(true, client))
    }

    @Test
    fun login_error_throwLoginFailedException(){
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)

        lenient().`when`(SUT.execute(client, "pepe"))
            .thenThrow(LoginFailedException::class.java)
    }


}