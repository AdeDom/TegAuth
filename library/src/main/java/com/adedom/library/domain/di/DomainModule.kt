package com.adedom.library.domain.di

import com.adedom.library.domain.usecase.*
import com.adedom.library.presentation.usercase.*
import org.koin.dsl.module

private val domainModule = module {

    single<SignInUseCase> { SignInUseCaseImpl(get(), get()) }
    single<SignUpUseCase> { SignUpUseCaseImpl(get(), get()) }
    single<SplashScreenUseCase> { SplashScreenUseCaseImpl(get()) }
    single<MainUseCase> { MainUseCaseImpl(get(), get()) }
    single<ChangePasswordUseCase> { ChangePasswordUseCaseImpl(get(), get()) }
    single<ChangeProfileUseCase> { ChangeProfileUseCaseImpl(get()) }

}

val getDomainModule = domainModule
