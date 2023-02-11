package com.edival.recioblog.presentation.screens.new_posts

data class NewPostState(
    var imgUrl: String? = null,
    var name: String = "",
    var description: String = "",
    var category: String = ""
)