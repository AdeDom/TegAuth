package com.adedom.library.presentation.changeimage

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adedom.library.base.BaseViewModel
import com.adedom.library.data.db.entities.PlayerInfoEntity
import com.adedom.library.domain.Resource
import com.adedom.library.presentation.usercase.ChangeImageUseCase
import com.adedom.teg.models.response.BaseResponse
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

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
            val request = context.convertToMultipartBody(fileUri)
            when (val resource = useCase.callChangeImageProfile(request)) {
                is Resource.Success -> _changeImageProfileEvent.value = resource.data
                is Resource.Error -> setError(resource)
            }

            setState { copy(loading = false) }
        }
    }

    fun Context.convertToMultipartBody(fileUri: Uri): MultipartBody.Part {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(fileUri, "r", null)

        val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        val file = File(cacheDir, contentResolver.getFileName(fileUri))
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        val requestFile = RequestBody
            .create(contentResolver.getType(fileUri)?.toMediaTypeOrNull(), file)

        return MultipartBody.Part.createFormData("imageFile", file.name, requestFile)
    }

    fun ContentResolver.getFileName(fileUri: Uri): String {
        var name = ""
        val returnCursor = this.query(fileUri, null, null, null, null)
        if (returnCursor != null) {
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            name = returnCursor.getString(nameIndex)
            returnCursor.close()
        }
        return name
    }

}
