package com.adedom.tegauth.presentation.di

import com.adedom.tegauth.presentation.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { SignInViewModel(get()) }

}
