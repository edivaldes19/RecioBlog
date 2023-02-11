package com.edival.recioblog.presentation.screens.my_posts

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
class MyPostViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases, private val authUseCases: AuthUseCases
) : ViewModel() {
    var postResponse by mutableStateOf<Response<List<Post>>?>(null)

    init {
        viewModelScope.launch {
            authUseCases.getCurrentUser()?.let { user ->
                postResponse = Response.Loading
                postsUseCases.getPostsByUserId(user.uid).collect { response ->
                    postResponse = response
                }
            }
        }
    }

    fun deletePost(id: String, imgUrl: String?): Job = viewModelScope.launch {
        postsUseCases.deletePost(id, imgUrl)
    }
}