package com.adedom.library.domain.usecase

import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegRepository
import com.adedom.library.presentation.usercase.ChangePasswordUseCase
import com.adedom.library.sharedpreference.service.SessionManagerService
import com.adedom.teg.models.request.ChangePasswordRequest
import com.adedom.teg.models.response.BaseResponse

class ChangePasswordUseCaseImpl(
    private val repository: DefaultTegRepository,
    private val sessionManagerService: SessionManagerService,
) : ChangePasswordUseCase {

    override suspend fun callChangePassword(changePassword: ChangePasswordRequest): Resource<BaseResponse> {
        val resource = repository.callChangePassword(changePassword)

        when (resource) {
            is Resource.Success -> {
                val result = resource.data
                if (result.success) {
                    sessionManagerService.accessToken = ""
                    sessionManagerService.refreshToken = ""
                }
            }
        }

        return resource
    }

}
