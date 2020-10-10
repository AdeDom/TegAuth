package com.adedom.library.domain.usecase

import com.adedom.library.data.db.entities.PlayerInfoEntity
import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegRepository
import com.adedom.library.presentation.usercase.ChangeProfileUseCase
import com.adedom.teg.models.request.ChangeProfileRequest
import com.adedom.teg.models.response.BaseResponse

class ChangeProfileUseCaseImpl(
    private val repository: DefaultTegRepository,
) : ChangeProfileUseCase {

    override suspend fun callChangeProfile(changeProfile: ChangeProfileRequest): Resource<BaseResponse> {
        val resource = repository.callChangeProfile(changeProfile)

        when (resource) {
            is Resource.Success -> {
                val result = resource.data
                if (result.success) {
                    callFetchPlayerInfo()
                }
            }
        }

        return resource
    }

    private suspend fun callFetchPlayerInfo() {
        when (val resource = repository.callFetchPlayerInfo()) {
            is Resource.Success -> {
                if (resource.data.success) {
                    val result = resource.data.playerInfo

                    val playerInfoEntity = PlayerInfoEntity(
                        playerId = result?.playerId.orEmpty(),
                        username = result?.username,
                        name = result?.name,
                        image = result?.image,
                        level = result?.level,
                        state = result?.state,
                        gender = result?.gender,
                        birthDate = result?.birthdate,
                    )

                    repository.savePlayerInfo(playerInfoEntity)
                } else {
                    callFetchPlayerInfo()
                }
            }
            is Resource.Error -> callFetchPlayerInfo()
        }
    }

}
