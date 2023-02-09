package com.edival.recioblog.presentation.screens.signup

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)