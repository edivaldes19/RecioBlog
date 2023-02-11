package com.edival.recioblog.domain.use_cases.auth

import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPassword
@Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String): Response<String> = repository.resetPassword(email)
}