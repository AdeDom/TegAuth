package com.adedom.library.domain.usecase

import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegRepository
import com.adedom.library.presentation.usercase.MainUseCase
import com.adedom.library.sharedpreference.service.SessionManagerService
import com.adedom.teg.models.response.PlayerInfoResponse

class MainUseCaseImpl(
    private val repository: DefaultTegRepository,
    private val sessionManagerService: SessionManagerService,
) : MainUseCase {

    override suspend fun fetchPlayerInfo(): Resource<PlayerInfoResponse> {
        val resource = repository.callFetchPlayerInfo()
        return resource
    }

    override fun signOut() {
        sessionManagerService.accessToken = ""
        sessionManagerService.refreshToken = ""
    }

}
