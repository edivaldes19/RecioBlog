package com.edival.recioblog.domain.use_cases.posts

import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.repository.PostsRepository
import javax.inject.Inject

class LikePost @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(idPost: String, idUser: String): Response<Boolean> {
        return repository.like(idPost, idUser)
    }
}