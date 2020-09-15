package com.adedom.library.domain.repository

import com.adedom.library.domain.Resource
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse

interface DefaultTegAuthRepository {

    suspend fun callSignIn(signIn: SignInRequest): Resource<SignInResponse>

}
