package com.adedom.library.data.di

import com.adedom.library.data.network.source.DataSourceProvider
import com.adedom.library.data.network.source.TegDataSource
import com.adedom.library.data.repository.DefaultTegAuthRepositoryImpl
import com.adedom.library.domain.repository.DefaultTegRepository
import org.koin.dsl.module

private val dataModule = module {

    single { DataSourceProvider(get()) }

    single { TegDataSource(get()) }

    single<DefaultTegRepository> { DefaultTegAuthRepositoryImpl(get()) }

}

val getDataModule = dataModule
