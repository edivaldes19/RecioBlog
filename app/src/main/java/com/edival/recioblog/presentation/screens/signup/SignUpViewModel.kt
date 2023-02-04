package com.edival.recioblog.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject constructor() : ViewModel() {
    var username: MutableState<String> = mutableStateOf("")
    private var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrMsg: MutableState<String?> = mutableStateOf(null)
    var email: MutableState<String> = mutableStateOf("")
    private var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String?> = mutableStateOf(null)
    var password: MutableState<String> = mutableStateOf("")
    private var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String?> = mutableStateOf(null)
    var confirmPassword: MutableState<String> = mutableStateOf("")
    private var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg: MutableState<String?> = mutableStateOf(null)
    var isEnabledSignUpButton: MutableState<Boolean> = mutableStateOf(false)
    fun validateUsername() {
        if (username.value.length >= 5) {
            isUsernameValid.value = true
            usernameErrMsg.value = null
        } else {
            isUsernameValid.value = false
            usernameErrMsg.value = "El nombre de usuario debe ser mayor o igual a 5 caracteres."
        }
        isEnabledSignUpButton.value =
            isUsernameValid.value && isEmailValid.value && isPasswordValid.value && isConfirmPasswordValid.value
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = null
        } else {
            isEmailValid.value = false
            emailErrMsg.value = "Correo electrónico inválido."
        }
        isEnabledSignUpButton.value =
            isUsernameValid.value && isEmailValid.value && isPasswordValid.value && isConfirmPasswordValid.value
    }

    fun validatePassword() {
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            passwordErrMsg.value = null
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = "La contraseña debe ser mayor o igual a 6 caracteres."
        }
        isEnabledSignUpButton.value =
            isUsernameValid.value && isEmailValid.value && isPasswordValid.value && isConfirmPasswordValid.value
    }

    fun validateBothPasswords() {
        if (password.value.length == confirmPassword.value.length) {
            isConfirmPasswordValid.value = true
            confirmPasswordErrMsg.value = null
        } else {
            isConfirmPasswordValid.value = false
            confirmPasswordErrMsg.value = "Las contraseñas no coinciden."
        }
        isEnabledSignUpButton.value =
            isUsernameValid.value && isEmailValid.value && isPasswordValid.value && isConfirmPasswordValid.value
    }
}