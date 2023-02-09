package com.edival.recioblog.data.repository

import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(private val auth: FirebaseAuth) : AuthRepository {
    override val currentUser: FirebaseUser? get() = auth.currentUser
    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result.user!!)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun signUp(user: User): Response<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(user.email!!, user.password!!).await()
            Response.Success(result.user!!)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override fun logOut() {
        auth.signOut()
    }
}