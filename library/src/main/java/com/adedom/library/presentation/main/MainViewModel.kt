package com.adedom.library.presentation.main

import com.adedom.library.base.BaseViewModel
import com.adedom.library.domain.Resource
import com.adedom.library.presentation.usercase.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: MainUseCase
) : BaseViewModel<MainState>(MainState()) {

    fun initialize() {
        launch {
            setState { copy(loading = true) }
            when (val resource = useCase.fetchPlayerInfo()) {
                is Resource.Success -> setState { copy(playerInfo = resource.data.playerInfo) }
                is Resource.Error -> setError(resource.throwable)
            }
            setState { copy(loading = false) }
        }
    }

    fun signOut() {
        useCase.signOut()
    }

}
