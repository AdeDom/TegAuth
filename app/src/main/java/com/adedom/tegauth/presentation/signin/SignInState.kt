package com.adedom.tegauth.presentation.signin

data class SignInState(
    val username: String = "",
    val password: String = "",
    val isValidUsername: Boolean = false,
    val isValidPassword: Boolean = false,
    val loading: Boolean = false
)
