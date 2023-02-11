package com.edival.recioblog.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.presentation.components.DefaultProgressBar
import com.edival.recioblog.presentation.screens.posts.PostViewModel

@Composable
fun UnlikePosts(viewModel: PostViewModel = hiltViewModel()) {
    when (val response = viewModel.unlikePostResponse) {
        Response.Loading -> DefaultProgressBar()
        is Response.Success -> viewModel.unlikePostResponse = null
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