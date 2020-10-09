package com.adedom.library.domain.usecase

import com.adedom.library.presentation.usercase.MainUseCase
import com.adedom.library.sharedpreference.service.SessionManagerService

class MainUseCaseImpl(
    private val sessionManagerService: SessionManagerService,
) : MainUseCase {

    override fun signOut() {
        sessionManagerService.accessToken = ""
        sessionManagerService.refreshToken = ""
    }

}
