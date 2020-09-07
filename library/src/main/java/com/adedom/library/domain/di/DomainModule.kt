package com.adedom.library.domain.di

import com.adedom.library.domain.usecase.SignInUseCase
import com.adedom.library.domain.usecase.SignInUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    single<SignInUseCase> { SignInUseCaseImpl(get()) }

}
