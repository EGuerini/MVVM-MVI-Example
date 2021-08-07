package com.eguerini.veritrancodeexercise.di.annotation

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Choose(val value: String = "")
