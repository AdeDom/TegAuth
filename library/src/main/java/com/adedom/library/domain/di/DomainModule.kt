package com.adedom.library.domain.di

import com.adedom.library.presentation.usercase.SignInUseCase
import com.adedom.library.domain.usecase.SignInUseCaseImpl
import org.koin.dsl.module

private val domainModule = module {

    single<SignInUseCase> { SignInUseCaseImpl(get()) }

}

val getDomainModule = domainModule
