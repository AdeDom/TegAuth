package com.adedom.tegauth.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adedom.library.domain.model.ValidateSignIn
import com.adedom.library.domain.usecase.SignInUseCase
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse
import com.adedom.tegauth.base.BaseViewModel
import kotlinx.coroutines.launch

data class SignInState(
    val username: String = "",
    val password: String = "",
    val isValidUsername: Boolean = false,
    val isValidPassword: Boolean = false,
    val loading: Boolean = false
)

class SignInViewModel(
    private val useCase: SignInUseCase
) : BaseViewModel<SignInState>(SignInState()) {

    private val _signInEvent = MutableLiveData<ValidateSignIn>()
    val signInEvent: LiveData<ValidateSignIn>
        get() = _signInEvent

    private val _signIn = MutableLiveData<SignInResponse>()
    val signIn: LiveData<SignInResponse>
        get() = _signIn

    fun setUsername(username: String) {
        setState {
            copy(
                username = username,
                isValidUsername = useCase.validateUsername(username)
            )
        }
    }

    fun setPassword(password: String) {
        setState {
            copy(
                password = password,
                isValidPassword = useCase.validatePassword(password)
            )
        }
    }

    fun onSignIn() {
        val signIn = SignInRequest(
            username = state.value?.username,
            password = state.value?.password
        )
        _signInEvent.value = useCase.validateSignIn(signIn)
    }

    fun callSignIn() {
        launch {
            try {
                setState { copy(loading = true) }
                val request = SignInRequest(
                    username = state.value?.username,
                    password = state.value?.password
                )
                val response = useCase.callSignIn(request)
                _signIn.value = response
                setState { copy(loading = false) }
            } catch (e: Throwable) {
                setState { copy(loading = false) }
                setError(e)
            }
        }
    }

}
