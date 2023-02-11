package com.edival.recioblog.presentation.screens.my_posts.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.edival.recioblog.domain.model.Post

@Composable
fun MyPostContent(
    paddingValues: PaddingValues, navController: NavHostController, posts: List<Post>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) { items(items = posts) { post -> MyPostCard(navController = navController, post = post) } }
}