package com.adedom.library.presentation.changepassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adedom.library.base.BaseViewModel
import com.adedom.library.domain.Resource
import com.adedom.library.presentation.usercase.ChangePasswordUseCase
import com.adedom.teg.models.request.ChangePasswordRequest
import com.adedom.teg.models.response.BaseResponse
import kotlinx.coroutines.launch

class ChangePasswordViewModel(
    private val useCase: ChangePasswordUseCase,
) : BaseViewModel<ChangePasswordState>(ChangePasswordState()) {

    private val _changePasswordEvent = MutableLiveData<BaseResponse>()
    val changePasswordEvent: LiveData<BaseResponse>
        get() = _changePasswordEvent

    fun setStateOldPassword(oldPassword: String) {
        setState { copy(oldPassword = oldPassword) }
    }

    fun setStateNewPassword(newPassword: String) {
        setState { copy(newPassword = newPassword) }
    }

    fun setStateReNewPassword(reNewPassword: String) {
        setState { copy(reNewPassword = reNewPassword) }
    }

    fun callChangePassword() {
        launch {
            setState { copy(loading = true) }
            val request = ChangePasswordRequest(
                oldPassword = state.value?.oldPassword,
                newPassword = state.value?.newPassword,
            )
            when (val resource = useCase.callChangePassword(request)) {
                is Resource.Success -> _changePasswordEvent.value = resource.data
                is Resource.Error -> setError(resource)
            }
            setState { copy(loading = false) }
        }
    }

}
