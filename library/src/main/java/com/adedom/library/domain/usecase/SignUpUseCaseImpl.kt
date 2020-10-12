package com.adedom.library.domain.usecase

import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegRepository
import com.adedom.library.presentation.usercase.SignUpUseCase
import com.adedom.library.sharedpreference.service.SessionManagerService
import com.adedom.teg.models.request.SignUpRequest
import com.adedom.teg.models.response.SignInResponse

class SignUpUseCaseImpl(
    private val repository: DefaultTegRepository,
    private val sessionManagerService: SessionManagerService,
) : SignUpUseCase {

    override suspend fun callSignUp(signUp: SignUpRequest): Resource<SignInResponse> {
        val resource = repository.callSignUp(signUp)

        when (resource) {
            is Resource.Success -> {
                val response = resource.data
                if (response.success) {
                    val accessToken = response.token?.accessToken.orEmpty()
                    val refreshToken = response.token?.refreshToken.orEmpty()
                    sessionManagerService.accessToken = accessToken
                    sessionManagerService.refreshToken = refreshToken
                }
            }
        }

        return resource
    }

}
