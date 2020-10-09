package com.adedom.library.presentation.usercase

import com.adedom.library.domain.Resource
import com.adedom.teg.models.request.SignUpRequest
import com.adedom.teg.models.response.SignInResponse

interface SignUpUseCase {

    suspend fun callSignUp(signUp: SignUpRequest): Resource<SignInResponse>

}
