package com.adedom.library.presentation.changeimage

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adedom.library.base.BaseViewModel
import com.adedom.library.data.db.entities.PlayerInfoEntity
import com.adedom.library.domain.Resource
import com.adedom.library.presentation.usercase.ChangeImageUseCase
import com.adedom.library.util.convertMultipartBodyPart
import com.adedom.teg.models.response.BaseResponse
import kotlinx.coroutines.launch

class ChangeImageViewModel(
    private val context: Context,
    private val useCase: ChangeImageUseCase,
) : BaseViewModel<ChangeImageState>(ChangeImageState()) {

    private val _changeImageProfileEvent = MutableLiveData<BaseResponse>()
    val changeImageProfileEvent: LiveData<BaseResponse>
        get() = _changeImageProfileEvent

    val fetchPlayerInfo: LiveData<PlayerInfoEntity>
        get() = useCase.fetchPlayerInfo()

    fun setStateImage(imageUri: String) {
        setState { copy(imageUri = imageUri, isImageUri = true) }
    }

    fun callChangeImageProfile() {
        launch {
            setState { copy(loading = true) }

            val fileUri = Uri.parse(state.value?.imageUri)
            val request = context.convertMultipartBodyPart(fileUri, "imageFile")
            when (val resource = useCase.callChangeImageProfile(request)) {
                is Resource.Success -> _changeImageProfileEvent.value = resource.data
                is Resource.Error -> setError(resource)
            }

            setState { copy(loading = false) }
        }
    }

}
