package com.adedom.library.presentation.changepassword

data class ChangePasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val reNewPassword: String = "",
    val loading: Boolean = false,
)
