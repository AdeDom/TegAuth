package com.adedom.library.domain.usecase

import com.adedom.library.domain.model.ValidateSignIn
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse

interface SignInUseCase {

    suspend fun callSignIn(signIn: SignInRequest): SignInResponse

    fun validateSignIn(signIn: SignInRequest): ValidateSignIn

    fun validateUsername(username: String): Boolean

    fun validatePassword(password: String): Boolean

}
