package com.adedom.tegauth

import android.app.Application
import com.adedom.library.data.di.getDataModule
import com.adedom.library.domain.di.getDomainModule
import com.adedom.library.presentation.di.getPresentationModule
import com.adedom.library.sharedpreference.di.getSharedPreferenceModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            koin.loadModules(
                listOf(
                    getPresentationModule,
                    getDomainModule,
                    getDataModule,
                    getSharedPreferenceModule
                )
            )
            koin.createRootScope()
        }

        Stetho.initializeWithDefaults(this)
    }
}
