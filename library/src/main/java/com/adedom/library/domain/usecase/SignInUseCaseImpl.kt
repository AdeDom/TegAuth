package com.adedom.library.domain.usecase

import com.adedom.library.domain.Constant
import com.adedom.library.domain.Resource
import com.adedom.library.domain.model.ValidateSignIn
import com.adedom.library.domain.repository.DefaultTegAuthRepository
import com.adedom.library.presentation.usercase.SignInUseCase
import com.adedom.teg.models.request.SignInRequest
import com.adedom.teg.models.response.SignInResponse

class SignInUseCaseImpl(
    private val repository: DefaultTegAuthRepository
) : SignInUseCase {

    override suspend fun callSignIn(signIn: SignInRequest): Resource<SignInResponse> {
        return repository.callSignIn(signIn)
    }

    override fun validateSignIn(signIn: SignInRequest): ValidateSignIn {
        val username = signIn.username.orEmpty()
        val password = signIn.password.orEmpty()
        return when {
            signIn.username == null -> ValidateSignIn.VALIDATE_ERROR
            username.isBlank() -> ValidateSignIn.USERNAME_EMPTY
            username.length < Constant.USERNAME_LENGTH -> ValidateSignIn.USERNAME_INCORRECT
            signIn.password == null -> ValidateSignIn.VALIDATE_ERROR
            password.isBlank() -> ValidateSignIn.PASSWORD_EMPTY
            password.length < Constant.PASSWORD_LENGTH -> ValidateSignIn.PASSWORD_INCORRECT
            else -> ValidateSignIn.VALIDATE_SUCCESS
        }
    }

    override fun validateUsername(username: String): Boolean {
        return username.isNotBlank() && username.length >= Constant.USERNAME_LENGTH
    }

    override fun validatePassword(password: String): Boolean {
        return password.isNotBlank() && password.length >= Constant.PASSWORD_LENGTH
    }

}
