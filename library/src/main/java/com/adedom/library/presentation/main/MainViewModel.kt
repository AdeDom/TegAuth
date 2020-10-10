package com.adedom.library.presentation.main

import com.adedom.library.base.BaseViewModel
import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegRepository
import com.adedom.library.presentation.usercase.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: MainUseCase,
    private val repository: DefaultTegRepository,
) : BaseViewModel<MainState>(MainState()) {

    fun fetchPlayerInfo() {
        launch {
            setState { copy(loading = true) }
            val playerInfo = useCase.fetchPlayerInfo()
            setState { copy(loading = false, playerInfo = playerInfo) }
        }
    }

    fun callPlayerState(state: String) {
        launch {
            when (val resource = repository.callPlayerState(state)) {
                is Resource.Error -> setError(resource)
            }
        }
    }

    fun signOut() {
        launch {
            useCase.signOut()
        }
    }

}
