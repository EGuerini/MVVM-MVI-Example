package com.eguerini.veritrancodeexercise.transfer.data.di

import com.eguerini.veritrancodeexercise.transfer.domain.interactor.TransferRepository
import com.eguerini.veritrancodeexercise.transfer.model.repositories.TransferRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TransferRepositoryModule {

    @Provides
    @Singleton
    fun transferRepository(): TransferRepository = TransferRepositoryImpl()
}