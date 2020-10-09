package com.adedom.library.domain.usecase

import com.adedom.library.domain.Resource
import com.adedom.library.domain.model.ValidateSignIn
import com.adedom.teg.models.request.SignInRequest
import com.adedom.teg.models.response.SignInResponse

interface SignInUseCase {

    suspend fun callSignIn(signIn: SignInRequest): Resource<SignInResponse>

    fun validateSignIn(signIn: SignInRequest): ValidateSignIn

    fun validateUsername(username: String): Boolean

    fun validatePassword(password: String): Boolean

}
