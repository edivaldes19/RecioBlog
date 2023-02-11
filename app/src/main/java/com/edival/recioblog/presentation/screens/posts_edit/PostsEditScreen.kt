package com.edival.recioblog.presentation.screens.posts_edit

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultTopBar
import com.edival.recioblog.presentation.screens.posts_edit.components.PostsEditContent
import com.edival.recioblog.presentation.screens.posts_edit.components.UpdatePost

@Composable
fun PostsEditScreen(navController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            title = stringResource(id = R.string.edit_post),
            upAvailable = true,
            navController = navController
        )
    }, content = { padding -> PostsEditContent(paddingValues = padding) })
    UpdatePost(navController = navController)
}