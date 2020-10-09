package com.adedom.library.presentation.splashscreen

import com.adedom.library.base.BaseViewModel
import com.adedom.library.presentation.usercase.SplashScreenUseCase

class SplashScreenViewModel(
    private val userCase: SplashScreenUseCase,
) : BaseViewModel<SplashScreenState>(SplashScreenState) {

    fun initialize() = userCase.isValidateSignIn()

}
