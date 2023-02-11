package com.edival.recioblog.presentation.screens.new_posts

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultTopBar
import com.edival.recioblog.presentation.screens.new_posts.components.NewPost
import com.edival.recioblog.presentation.screens.new_posts.components.NewPostsContent

@Composable
fun NewPostsScreen(navController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            title = stringResource(id = R.string.new_post),
            upAvailable = true,
            navController = navController
        )
    }, content = { padding -> NewPostsContent(paddingValues = padding) })
    NewPost(navController = navController)
}