package com.edival.recioblog.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.use_cases.auth.AuthUseCases
import com.edival.recioblog.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases
) : ViewModel() {
    var userData by mutableStateOf(User())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCases.getCurrentUser()?.let { user ->
                usersUseCases.getUserById(user.uid).collect { userRT -> userData = userRT }
            }
        }
    }

    fun logOut() {
        authUseCases.logOut()
    }
}