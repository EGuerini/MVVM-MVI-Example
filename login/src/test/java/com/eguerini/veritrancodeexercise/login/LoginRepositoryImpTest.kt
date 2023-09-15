package com.eguerini.veritrancodeexercise.login

import com.eguerini.veritrancodeexercise.login.domain.interactor.LoginRepository
import com.eguerini.veritrancodeexercise.login.data.repositories.LoginRepositoryImpl
import com.eguerini.veritrancodeexercise.login.domain.result.LoginResult
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
class LoginRepositoryImpTest {

    @Mock
    private lateinit var SUT: LoginRepositoryImpl

    @Mock
    lateinit var account: Account
    @Mock
    lateinit var client: Client

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        SUT = mock(LoginRepositoryImpl::class.java)
    }

    private fun givenAnClientAndAccount(){
        account = Account(
            accountBalance = BalanceVO(BigDecimal(100)),
            cbu = "1234567891011121314150",
            alias = "fran.guerini"
        )
        client = Client(id = "francisco", account = account)
    }

    @Test
    fun login_ok(){
        givenAnClientAndAccount()

        lenient().`when`(SUT.login(client, "francisco"))
            .thenReturn(LoginResult(true, client))
    }

    @Test
    fun login_error_throwLoginFailedException(){
        givenAnClientAndAccount()

        lenient().`when`(SUT.login(client, "pepe"))
            .thenThrow(RuntimeException::class.java)
    }


}