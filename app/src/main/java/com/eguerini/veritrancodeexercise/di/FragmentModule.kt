package com.eguerini.veritrancodeexercise.di

import com.eguerini.veritrancodeexercise.ui.view.fragment.FeaturesFragment
import com.eguerini.veritrancodeexercise.ui.view.fragment.LoginFragment
import com.eguerini.veritrancodeexercise.ui.view.fragment.TransferFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeFeaturesFragment(): FeaturesFragment

    @ContributesAndroidInjector
    abstract fun contributeTransferFragment(): TransferFragment
}