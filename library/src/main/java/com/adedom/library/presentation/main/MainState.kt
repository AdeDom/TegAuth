package com.adedom.library.presentation.main

import com.adedom.teg.models.response.PlayerInfo

data class MainState(
    val playerInfo: PlayerInfo? = null,
    val loading: Boolean = false,
)
