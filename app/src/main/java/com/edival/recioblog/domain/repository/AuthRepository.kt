package com.edival.recioblog.domain.repository

import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun signUp(user: User): Response<FirebaseUser>
    suspend fun resetPassword(email: String): Response<String>
    fun logOut()
}