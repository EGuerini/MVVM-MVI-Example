package com.eguerini.veritrancodeexercise.di.component

import com.eguerini.veritrancodeexercise.app.BaseApplication
import com.eguerini.veritrancodeexercise.data.di.DepositRepositoryModule
import com.eguerini.veritrancodeexercise.data.di.WithdrawalRepositoryModule
import com.eguerini.veritrancodeexercise.di.ActivityModule
import com.eguerini.veritrancodeexercise.di.EntityModule
import com.eguerini.veritrancodeexercise.di.FragmentModule
import com.eguerini.veritrancodeexercise.di.ViewModelModule
import com.eguerini.veritrancodeexercise.domain.di.DepositUseCaseModule
import com.eguerini.veritrancodeexercise.domain.di.WithdrawalUseCaseModule
import com.eguerini.veritrancodeexercise.login.data.di.LoginRepositoryModule
import com.eguerini.veritrancodeexercise.login.domain.di.LoginUseCaseModule
import com.eguerini.veritrancodeexercise.transfer.data.di.TransferRepositoryModule
import com.eguerini.veritrancodeexercise.transfer.domain.di.TransferUseCaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        EntityModule::class,
        LoginRepositoryModule::class,
        LoginUseCaseModule::class,
        DepositUseCaseModule::class,
        DepositRepositoryModule::class,
        WithdrawalUseCaseModule::class,
        WithdrawalRepositoryModule::class,
        TransferUseCaseModule::class,
        TransferRepositoryModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun applicationBind(application: BaseApplication): Builder
    }
}