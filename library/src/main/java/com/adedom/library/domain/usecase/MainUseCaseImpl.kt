package com.adedom.library.domain.usecase

import com.adedom.library.data.db.entities.PlayerInfoEntity
import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegRepository
import com.adedom.library.presentation.usercase.MainUseCase
import com.adedom.library.sharedpreference.service.SessionManagerService
import com.adedom.teg.models.response.PlayerInfo

class MainUseCaseImpl(
    private val repository: DefaultTegRepository,
    private val sessionManagerService: SessionManagerService,
) : MainUseCase {

    override suspend fun fetchPlayerInfo(): PlayerInfo? {
        if (repository.getDbPlayerInfo() == null) {
            val resource = repository.callFetchPlayerInfo()

            if (resource is Resource.Success) {
                val playerInfo = resource.data.playerInfo
                val playerInfoEntity = PlayerInfoEntity(
                    playerId = playerInfo?.playerId.orEmpty(),
                    username = playerInfo?.username,
                    name = playerInfo?.name,
                    image = playerInfo?.image,
                    level = playerInfo?.level,
                    state = playerInfo?.state,
                    gender = playerInfo?.gender,
                    birthDate = playerInfo?.birthdate,
                )
                repository.savePlayerInfo(playerInfoEntity)
            }
        }

        val db = repository.getDbPlayerInfo()
        val playerInfo = PlayerInfo(
            playerId = db?.playerId,
            username = db?.username,
            name = db?.name,
            image = db?.image,
            level = db?.level,
            state = db?.state,
            gender = db?.gender,
            birthdate = db?.birthDate,
        )

        return playerInfo
    }

    override suspend fun signOut() {
        sessionManagerService.accessToken = ""
        sessionManagerService.refreshToken = ""
        repository.deletePlayerInfo()
    }

}
