package com.adedom.library.presentation.main

import com.adedom.library.base.BaseViewModel
import com.adedom.library.presentation.usercase.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: MainUseCase
) : BaseViewModel<MainState>(MainState()) {

    fun fetchPlayerInfo() {
        launch {
            setState { copy(loading = true) }
            val playerInfo = useCase.fetchPlayerInfo()
            setState { copy(loading = false, playerInfo = playerInfo) }
        }
    }

    fun signOut() {
        launch {
            useCase.signOut()
        }
    }

}
