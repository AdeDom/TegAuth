package com.adedom.library.presentation.main

import androidx.lifecycle.LiveData
import com.adedom.library.base.BaseViewModel
import com.adedom.library.data.db.entities.PlayerInfoEntity
import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegRepository
import com.adedom.library.presentation.usercase.MainUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: MainUseCase,
    private val repository: DefaultTegRepository,
) : BaseViewModel<MainState>(MainState()) {

    val playerInfo: LiveData<PlayerInfoEntity>
        get() = repository.getDbPlayerInfoLiveData()

    fun fetchPlayerInfo() {
        launch {
            setState { copy(loading = true) }
            val callApi = useCase.fetchPlayerInfo()
            if (!callApi) fetchPlayerInfo()
            setState { copy(loading = false) }
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
            val signOut = useCase.signOut()
            if (!signOut) signOut()
        }
    }

}
