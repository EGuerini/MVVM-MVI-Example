package com.eguerini.veritrancodeexercise.di

import com.eguerini.veritrancodeexercise.di.annotation.Choose
import com.eguerini.veritrancodeexercise.model.entities.AccountModel
import com.eguerini.veritrancodeexercise.model.entities.ClientModel
import com.eguerini.veritrancodeexercise.model.vo.BalanceVOView
import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
open class EntityModule {

    @Provides
    fun provideBalanceVOView(amount: BigDecimal): BalanceVOView = BalanceVOView(amount)

    @Provides
    fun provideString(): String = String()

    @Provides
    fun providesBigDecimal(): BigDecimal = BigDecimal.ZERO

    @Provides
    @Choose("emanuelAccount")
    open fun emanuelAccount(): AccountModel = AccountModel(
        accountNbr = "0987654321",
        accountBalance = BalanceVOView(BigDecimal(200)),
        cbu = "0151413121110987654321",
        alias = "ema.guerini"
    )

    @Provides
    @Choose("franciscoAccount")
    open fun franciscoAccount(): AccountModel = AccountModel(
        accountNbr = "1234567890",
        accountBalance = BalanceVOView(BigDecimal(100)),
        cbu = "1234567891011121314150",
        alias = "fran.guerini"
    )

    @Provides
    @Choose("emanuel")
    open fun emanuel(@Choose("emanuelAccount")  emanuelAccount: AccountModel): ClientModel = ClientModel(
        id = "emanuel",
        user = "ema1234",
        password = "1234",
        name = "Emanuel",
        surname = "Güerini",
        account = emanuelAccount
    )

    @Provides
    @Choose("francisco")
    open fun francisco(@Choose("franciscoAccount") franciscoAccount: AccountModel): ClientModel = ClientModel(
        id = "francisco",
        user = "fran1234",
        password = "1234",
        name = "Francisco",
        surname = "Güerini",
        account = franciscoAccount
    )
}