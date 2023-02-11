package com.edival.recioblog.domain.use_cases.auth

import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class Login
@Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Response<FirebaseUser> {
        return repository.login(email, password)
    }
}