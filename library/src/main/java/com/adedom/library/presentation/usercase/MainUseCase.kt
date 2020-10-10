package com.adedom.library.presentation.usercase

import com.adedom.teg.models.response.PlayerInfo

interface MainUseCase {

    suspend fun fetchPlayerInfo(): PlayerInfo?

    suspend fun signOut()

}
