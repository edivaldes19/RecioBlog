package com.edival.recioblog.presentation.screens.signup

import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.use_cases.auth.AuthUseCases
import com.edival.recioblog.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel
@Inject constructor(
    private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases
) : ViewModel() {
    var state: SignUpState by mutableStateOf(SignUpState())
        private set
    private var _isUsernameValid: Boolean by mutableStateOf(false)
    var usernameErrMsg: String? by mutableStateOf(null)
        private set
    private var _isEmailValid: Boolean by mutableStateOf(false)
    var emailErrMsg: String? by mutableStateOf(null)
        private set
    private var _isPasswordValid: Boolean by mutableStateOf(false)
    var passwordErrMsg: String? by mutableStateOf(null)
        private set
    private var _isConfirmPasswordValid: Boolean by mutableStateOf(false)
    var confirmPasswordErrMsg: String? by mutableStateOf(null)
        private set
    var isEnabledSignUpButton: Boolean = false
    var signInResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    var user = User()
    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun signUp(): Job = viewModelScope.launch {
        user.username = state.username
        user.email = state.email
        user.password = state.password
        signInResponse = Response.Loading
        val result = authUseCases.signUp(user)
        signInResponse = result
    }

    fun createUser(): Job = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()?.uid
        usersUseCases.create(user)
    }

    fun validateUsername(ctx: Context) {
        if (state.username.length >= 5) {
            _isUsernameValid = true
            usernameErrMsg = null
        } else {
            _isUsernameValid = false
            usernameErrMsg = ctx.getString(R.string.username_err_msg)
        }
        isEnabledSignUpButton =
            _isUsernameValid && _isEmailValid && _isPasswordValid && _isConfirmPasswordValid
    }

    fun validateEmail(ctx: Context) {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            _isEmailValid = true
            emailErrMsg = null
        } else {
            _isEmailValid = false
            emailErrMsg = ctx.getString(R.string.email_err_msg)
        }
        isEnabledSignUpButton =
            _isUsernameValid && _isEmailValid && _isPasswordValid && _isConfirmPasswordValid
    }

    fun validatePassword(ctx: Context) {
        if (state.password.length >= 6) {
            _isPasswordValid = true
            passwordErrMsg = null
        } else {
            _isPasswordValid = false
            passwordErrMsg = ctx.getString(R.string.password_err_msg)
        }
        isEnabledSignUpButton =
            _isUsernameValid && _isEmailValid && _isPasswordValid && _isConfirmPasswordValid
    }

    fun validateBothPasswords(ctx: Context) {
        if (state.password == state.confirmPassword) {
            _isConfirmPasswordValid = true
            confirmPasswordErrMsg = null
        } else {
            _isConfirmPasswordValid = false
            confirmPasswordErrMsg = ctx.getString(R.string.confirm_password_err_msg)
        }
        isEnabledSignUpButton =
            _isUsernameValid && _isEmailValid && _isPasswordValid && _isConfirmPasswordValid
    }
}