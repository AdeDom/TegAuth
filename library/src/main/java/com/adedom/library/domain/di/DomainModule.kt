package com.adedom.library.domain.di

import com.adedom.library.domain.usecase.MainUseCaseImpl
import com.adedom.library.domain.usecase.SignInUseCaseImpl
import com.adedom.library.domain.usecase.SignUpUseCaseImpl
import com.adedom.library.domain.usecase.SplashScreenUseCaseImpl
import com.adedom.library.presentation.usercase.MainUseCase
import com.adedom.library.presentation.usercase.SignInUseCase
import com.adedom.library.presentation.usercase.SignUpUseCase
import com.adedom.library.presentation.usercase.SplashScreenUseCase
import org.koin.dsl.module

private val domainModule = module {

    single<SignInUseCase> { SignInUseCaseImpl(get(), get()) }
    single<SignUpUseCase> { SignUpUseCaseImpl(get(), get()) }
    single<SplashScreenUseCase> { SplashScreenUseCaseImpl(get()) }
    single<MainUseCase> { MainUseCaseImpl(get(), get()) }

}

val getDomainModule = domainModule
