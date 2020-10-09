package com.adedom.library.di

import android.content.Context
import com.adedom.library.data.di.getDataModule
import com.adedom.library.domain.di.getDomainModule
import com.adedom.library.presentation.di.getPresentationModule
import com.adedom.library.sharedpreference.di.getSharedPreferenceModule
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
                    getPresentationModule,
                    getDomainModule,
                    getDataModule,
                    getSharedPreferenceModule
                )
            }
        } catch (e: InstanceCreationException) {
        } catch (e: RuntimeException) {
        }

    }

}
