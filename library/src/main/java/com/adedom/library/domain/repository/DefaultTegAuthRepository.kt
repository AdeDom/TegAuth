package com.adedom.library.domain.repository

import com.adedom.library.domain.Resource
import com.adedom.teg.models.request.SignInRequest
import com.adedom.teg.models.response.SignInResponse

interface DefaultTegAuthRepository {

    suspend fun callSignIn(signIn: SignInRequest): Resource<SignInResponse>

}
