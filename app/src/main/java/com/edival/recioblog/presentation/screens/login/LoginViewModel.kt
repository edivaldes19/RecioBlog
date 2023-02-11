package com.edival.recioblog.presentation.screens.login

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {
    var state: LoginState by mutableStateOf(LoginState())
        private set
    var isEmailValid: Boolean by mutableStateOf(false)
        private set
    var emailErrMsg: String? by mutableStateOf(null)
        private set
    private var _isPasswordValid: Boolean by mutableStateOf(false)
    var passwordErrMsg: String? by mutableStateOf(null)
        private set
    var isEnabledLoginButton: Boolean = false
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set
    var resetPasswordResponse by mutableStateOf<Response<String>?>(null)
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

    fun login(): Job = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun resetPassword(): Job = viewModelScope.launch {
        if (isEmailValid) {
            resetPasswordResponse = Response.Loading
            val result = authUseCases.resetPassword(state.email)
            resetPasswordResponse = result
        }
    }

    fun validateEmail(ctx: Context) {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = null
        } else {
            isEmailValid = false
            emailErrMsg = ctx.getString(R.string.email_err_msg)
        }
        isEnabledLoginButton = isEmailValid && _isPasswordValid
    }

    fun validatePassword(ctx: Context) {
        if (state.password.length >= 6) {
            _isPasswordValid = true
            passwordErrMsg = null
        } else {
            _isPasswordValid = false
            passwordErrMsg = ctx.getString(R.string.password_err_msg)
        }
        isEnabledLoginButton = isEmailValid && _isPasswordValid
    }
}