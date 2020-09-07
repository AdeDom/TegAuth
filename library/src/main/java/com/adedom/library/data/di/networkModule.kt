package com.adedom.library.data.di

import com.adedom.library.data.network.NetworkServiceFactory
import com.adedom.library.data.network.ServiceFactory
import com.adedom.library.data.network.source.DataSourceProvider
import com.adedom.library.data.network.source.TegAuthDataSource
import com.adedom.library.data.repository.DefaultTegAuthRepositoryImpl
import com.adedom.library.domain.repository.DefaultTegAuthRepository
import org.koin.dsl.module

val dataModule = module {

    single<ServiceFactory> { NetworkServiceFactory(get()) }

    single { DataSourceProvider(get()) }

    single { TegAuthDataSource(get()) }

    single<DefaultTegAuthRepository> { DefaultTegAuthRepositoryImpl(get()) }

}
