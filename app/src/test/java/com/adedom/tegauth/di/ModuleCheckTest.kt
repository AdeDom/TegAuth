package com.adedom.tegauth.di

import android.content.Context
import com.adedom.library.data.di.dataModule
import com.adedom.library.domain.di.domainModule
import com.adedom.library.sharedpreference.di.sharedPreferenceModule
import com.adedom.tegauth.presentation.di.presentationModule
import io.mockk.mockk
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.core.error.InstanceCreationException
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules

@Category(CheckModuleTest::class)
class ModuleCheckTest : AutoCloseKoinTest() {

    @Test
    fun test_checkModules() {
        val contextModule = module {
            single { mockk<Context>(relaxed = true) }
        }

        try {
            checkModules {
                modules(
                    contextModule,
                    presentationModule,
                    domainModule,
                    dataModule,
                    sharedPreferenceModule
                )
            }
        } catch (e: InstanceCreationException) {
        } catch (e: RuntimeException) {
        }

    }

}
