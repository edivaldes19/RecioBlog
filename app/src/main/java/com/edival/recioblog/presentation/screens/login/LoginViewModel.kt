package com.edival.recioblog.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {
    var state: LoginState by mutableStateOf(LoginState())
        private set
    private var _isEmailValid: Boolean by mutableStateOf(false)
    var emailErrMsg: String? by mutableStateOf(null)
        private set
    private var _isPasswordValid: Boolean by mutableStateOf(false)
    var passwordErrMsg: String? by mutableStateOf(null)
        private set
    var isEnabledLoginButton: Boolean = false
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    init {
        authUseCases.getCurrentUser()?.let { user -> loginResponse = Response.Success(user) }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun login(): Job = viewModelScope.launch(Dispatchers.IO) {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            _isEmailValid = true
            emailErrMsg = null
        } else {
            _isEmailValid = false
            emailErrMsg = "Correo electrónico inválido."
        }
        isEnabledLoginButton = _isEmailValid && _isPasswordValid
    }

    fun validatePassword() {
        if (state.password.length >= 6) {
            _isPasswordValid = true
            passwordErrMsg = null
        } else {
            _isPasswordValid = false
            passwordErrMsg = "La contraseña debe ser mayor o igual a 6 caracteres."
        }
        isEnabledLoginButton = _isEmailValid && _isPasswordValid
    }
}