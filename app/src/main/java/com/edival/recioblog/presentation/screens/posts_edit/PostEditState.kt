package com.edival.recioblog.presentation.screens.posts_edit

data class PostEditState(
    var imgUrl: String? = null,
    var name: String = "",
    var description: String = "",
    var category: String = ""
)