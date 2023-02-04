package com.edival.recioblog.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.recioblog.presentation.screens.login.components.LoginBottomBar
import com.edival.recioblog.presentation.screens.login.components.LoginContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(topBar = {},
        content = { LoginContent() },
        bottomBar = { LoginBottomBar(navController = navController) })
}