package com.edival.recioblog.presentation.screens.detail_post

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultTopBar
import com.edival.recioblog.presentation.screens.detail_post.components.DetailPostContent

@Composable
fun DetailPostScreen(navController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            title = stringResource(id = R.string.post_detail),
            upAvailable = true,
            navController = navController
        )
    }, content = { paddingValues -> DetailPostContent(paddingValues = paddingValues) })
}