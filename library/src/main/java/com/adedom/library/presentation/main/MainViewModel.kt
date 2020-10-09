package com.adedom.library.presentation.main

import com.adedom.library.base.BaseViewModel
import com.adedom.library.presentation.usercase.MainUseCase

class MainViewModel(
    private val useCase: MainUseCase
) : BaseViewModel<MainState>(MainState) {

    fun signOut() {
        useCase.signOut()
    }

}
