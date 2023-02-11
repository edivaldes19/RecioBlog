package com.edival.recioblog.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.use_cases.auth.AuthUseCases
import com.edival.recioblog.domain.use_cases.posts.PostsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases, authUseCases: AuthUseCases
) : ViewModel() {
    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var likePostResponse by mutableStateOf<Response<Boolean>?>(null)
    var unlikePostResponse by mutableStateOf<Response<Boolean>?>(null)
    val currentUser = authUseCases.getCurrentUser()?.uid

    init {
        viewModelScope.launch {
            postsResponse = Response.Loading
            postsUseCases.getPosts().collect { response -> postsResponse = response }
        }
    }

    fun like(idPost: String, idUser: String): Job = viewModelScope.launch {
        likePostResponse = Response.Loading
        val result = postsUseCases.likePost(idPost, idUser)
        likePostResponse = result
    }

    fun unlike(idPost: String, idUser: String): Job = viewModelScope.launch {
        unlikePostResponse = Response.Loading
        val result = postsUseCases.unlikePost(idPost, idUser)
        unlikePostResponse = result
    }
}