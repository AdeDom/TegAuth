package com.adedom.library.domain.usecase

import com.adedom.library.presentation.usercase.SplashScreenUseCase
import com.adedom.library.sharedpreference.service.SessionManagerService

class SplashScreenUseCaseImpl(
    private val sessionManagerService: SessionManagerService,
) : SplashScreenUseCase {

    override fun isValidateSignIn(): Boolean {
        return !sessionManagerService.accessToken.isBlank()
    }

}
