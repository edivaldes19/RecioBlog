package com.edival.recioblog.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor() : ViewModel() {
    var email: MutableState<String> = mutableStateOf("")
    private var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String?> = mutableStateOf(null)
    var password: MutableState<String> = mutableStateOf("")
    private var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String?> = mutableStateOf(null)
    var isEnabledLoginButton: MutableState<Boolean> = mutableStateOf(false)
    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = null
        } else {
            isEmailValid.value = false
            emailErrMsg.value = "Correo electrónico inválido."
        }
        isEnabledLoginButton.value = isEmailValid.value && isPasswordValid.value
    }

    fun validatePassword() {
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            passwordErrMsg.value = null
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = "La contraseña debe ser mayor o igual a 6 caracteres."
        }
        isEnabledLoginButton.value = isEmailValid.value && isPasswordValid.value
    }
}