package com.eguerini.veritrancodeexercise.transfer.domain.di

import com.eguerini.veritrancodeexercise.transfer.data.di.TransferRepositoryModule
import com.eguerini.veritrancodeexercise.transfer.domain.interactor.TransferRepository
import com.eguerini.veritrancodeexercise.transfer.domain.usecases.TransferUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        TransferRepositoryModule::class
    ]
)
class TransferUseCaseModule {

    @Provides
    @Singleton
    fun transferUseCase(transferRepository: TransferRepository
    ): TransferUseCase =
        TransferUseCase(transferRepository)

}