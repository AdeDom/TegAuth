package com.adedom.library.presentation.usercase

import com.adedom.library.domain.Resource
import com.adedom.teg.models.response.PlayerInfoResponse

interface MainUseCase {

    suspend fun fetchPlayerInfo(): Resource<PlayerInfoResponse>

    fun signOut()

}
