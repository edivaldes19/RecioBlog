package com.edival.recioblog.domain.repository

import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostsRepository {
    suspend fun create(post: Post, file: File?): Response<Boolean>
    suspend fun update(post: Post, file: File?): Response<Boolean>
    suspend fun delete(idPost: String, imgUrl: String?): Response<Boolean>
    suspend fun like(idPost: String, idUser: String): Response<Boolean>
    suspend fun unlike(idPost: String, idUser: String): Response<Boolean>
    fun getPosts(): Flow<Response<List<Post>>>
    fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>>
}