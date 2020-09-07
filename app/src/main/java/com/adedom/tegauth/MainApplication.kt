package com.adedom.tegauth

import android.app.Application
import com.adedom.library.data.di.dataModule
import com.adedom.library.domain.di.domainModule
import com.adedom.library.sharedpreference.di.sharedPreferenceModule
import com.adedom.tegauth.presentation.di.presentationModule
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
                    presentationModule,
                    domainModule,
                    dataModule,
                    sharedPreferenceModule
                )
            )
            koin.createRootScope()
        }
    }
}
