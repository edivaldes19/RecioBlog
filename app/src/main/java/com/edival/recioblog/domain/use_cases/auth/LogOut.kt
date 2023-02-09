package com.edival.recioblog.domain.use_cases.auth

import com.edival.recioblog.domain.repository.AuthRepository
import javax.inject.Inject

class LogOut @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.logOut()
}