package com.edival.recioblog.domain.use_cases.posts

import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.repository.PostsRepository
import java.io.File
import javax.inject.Inject

class UpdatePost @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(post: Post, file: File?): Response<Boolean> {
        return repository.update(post, file)
    }
}