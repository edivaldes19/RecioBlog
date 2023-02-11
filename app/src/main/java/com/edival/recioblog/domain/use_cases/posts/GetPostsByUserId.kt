package com.edival.recioblog.domain.use_cases.posts

import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsByUserId @Inject constructor(private val repository: PostsRepository) {
    operator fun invoke(idUser: String): Flow<Response<List<Post>>> {
        return repository.getPostsByUserId(idUser)
    }
}