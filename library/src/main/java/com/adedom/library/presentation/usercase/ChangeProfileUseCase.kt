package com.adedom.library.presentation.usercase

import com.adedom.library.domain.Resource
import com.adedom.teg.models.request.ChangeProfileRequest
import com.adedom.teg.models.response.BaseResponse

interface ChangeProfileUseCase {

    suspend fun callChangeProfile(changeProfile: ChangeProfileRequest): Resource<BaseResponse>

}
