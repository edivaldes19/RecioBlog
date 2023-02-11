package com.edival.recioblog.presentation.screens.my_posts.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.presentation.components.DefaultProgressBar
import com.edival.recioblog.presentation.screens.my_posts.MyPostViewModel

@Composable
fun GetPostsByUserId(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: MyPostViewModel = hiltViewModel()
) {
    when (val response = viewModel.postResponse) {
        Response.Loading -> DefaultProgressBar()
        is Response.Success -> {
            MyPostContent(
                paddingValues = paddingValues, navController = navController, posts = response.data
            )
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