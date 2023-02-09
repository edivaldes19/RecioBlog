package com.edival.recioblog.presentation.screens.signup.components

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
import com.edival.recioblog.presentation.navigation.Graph
import com.edival.recioblog.presentation.screens.signup.SignUpViewModel

@Composable
fun SignUp(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
    when (val signupResponse = viewModel.signInResponse) {
        Response.Loading -> DefaultProgressBar()
        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                navController.apply {
                    popBackStack(Graph.AUTH, true)
                    navigate(route = Graph.HOME)
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                signupResponse.exception?.message ?: stringResource(id = R.string.unknown_error),
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }
}