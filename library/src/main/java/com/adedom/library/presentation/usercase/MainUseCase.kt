package com.adedom.library.presentation.usercase

interface MainUseCase {

    suspend fun fetchPlayerInfo(): Boolean = false

    suspend fun signOut(): Boolean = false

}
