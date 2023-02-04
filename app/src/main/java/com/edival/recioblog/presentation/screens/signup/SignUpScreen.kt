package com.edival.recioblog.presentation.screens.signup

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultTopBar
import com.edival.recioblog.presentation.screens.signup.components.SignUpContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignUpScreen(navController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            title = stringResource(id = R.string.new_user),
            upAvailable = true,
            navController = navController
        )
    }, content = { SignUpContent() }, bottomBar = {})
}