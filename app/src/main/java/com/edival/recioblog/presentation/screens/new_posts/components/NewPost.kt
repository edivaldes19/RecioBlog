package com.edival.recioblog.presentation.screens.new_posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.presentation.components.DefaultProgressBar
import com.edival.recioblog.presentation.screens.new_posts.NewPostViewModel

@Composable
fun NewPost(navController: NavHostController, viewModel: NewPostViewModel = hiltViewModel()) {
    when (val response = viewModel.createPostResponse) {
        Response.Loading -> DefaultProgressBar()
        is Response.Success -> {
            LaunchedEffect(Unit) { navController.popBackStack() }
            Toast.makeText(
                LocalContext.current, R.string.post_created_successfully, Toast.LENGTH_SHORT
            ).show()
        }
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: stringResource(id = R.string.unknown_error),
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }
}