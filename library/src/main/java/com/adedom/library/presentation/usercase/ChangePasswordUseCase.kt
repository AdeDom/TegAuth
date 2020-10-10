package com.adedom.library.presentation.usercase

import com.adedom.library.domain.Resource
import com.adedom.teg.models.request.ChangePasswordRequest
import com.adedom.teg.models.response.BaseResponse

interface ChangePasswordUseCase {

    suspend fun callChangePassword(changePassword: ChangePasswordRequest): Resource<BaseResponse>

}
