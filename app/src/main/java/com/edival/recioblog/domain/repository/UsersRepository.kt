package com.edival.recioblog.domain.repository

import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {
    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>
    suspend fun uploadImage(file: File): Response<String>
    fun getUserById(id: String): Flow<User>
}