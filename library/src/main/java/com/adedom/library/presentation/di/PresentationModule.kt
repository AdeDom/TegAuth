package com.adedom.library.presentation.di

import com.adedom.library.presentation.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val presentationModule = module {

    viewModel { SignInViewModel(get()) }

}

val getPresentationModule = presentationModule
