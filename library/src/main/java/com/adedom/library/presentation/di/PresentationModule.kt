package com.adedom.library.presentation.di

import com.adedom.library.presentation.changepassword.ChangePasswordViewModel
import com.adedom.library.presentation.changeprofile.ChangeProfileViewModel
import com.adedom.library.presentation.main.MainViewModel
import com.adedom.library.presentation.signin.SignInViewModel
import com.adedom.library.presentation.signup.SignUpViewModel
import com.adedom.library.presentation.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val presentationModule = module {

    viewModel { SignInViewModel(get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ChangePasswordViewModel(get()) }
    viewModel { ChangeProfileViewModel(get()) }

}

val getPresentationModule = presentationModule
