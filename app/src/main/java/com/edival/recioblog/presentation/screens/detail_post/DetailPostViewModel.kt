package com.edival.recioblog.presentation.screens.detail_post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.edival.recioblog.core.Constants
import com.edival.recioblog.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPostViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    var post = Post()

    init {
        savedStateHandle.get<String>(Constants.POST_ARG)?.let { postStr ->
            post = Post.fromJson(postStr)
        }
    }
}