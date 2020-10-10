package com.adedom.library.presentation.changeimage

data class ChangeImageState(
    val imageUri: String = "",
    val isImageUri: Boolean = false,
    val loading: Boolean = false,
)
