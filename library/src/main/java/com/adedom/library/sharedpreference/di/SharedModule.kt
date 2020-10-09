package com.adedom.library.sharedpreference.di

import com.adedom.library.sharedpreference.service.SessionManagerService
import com.adedom.library.sharedpreference.service.SessionManagerServiceImpl
import org.koin.dsl.module

private val sharedPreferenceModule = module {

    single<SessionManagerService> { SessionManagerServiceImpl(get()) }

}

val getSharedPreferenceModule = sharedPreferenceModule
