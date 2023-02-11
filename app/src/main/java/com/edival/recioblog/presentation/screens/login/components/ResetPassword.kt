package com.edival.recioblog.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.presentation.components.DefaultProgressBar
import com.edival.recioblog.presentation.screens.login.LoginViewModel

@Composable
fun ResetPassword(viewModel: LoginViewModel = hiltViewModel()) {
    when (val response = viewModel.resetPasswordResponse) {
        Response.Loading -> DefaultProgressBar()
        is Response.Success -> {
            Toast.makeText(
                LocalContext.current,
                stringResource(id = R.string.sent_email_reset_password, response.data),
                Toast.LENGTH_LONG
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