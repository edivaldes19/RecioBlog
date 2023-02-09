package com.edival.recioblog.domain.use_cases.auth

import com.edival.recioblog.domain.repository.AuthRepository
import javax.inject.Inject

class Login
@Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}