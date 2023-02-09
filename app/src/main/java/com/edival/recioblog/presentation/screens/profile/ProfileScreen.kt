package com.edival.recioblog.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.edival.recioblog.presentation.screens.profile.components.ProfileContent

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(topBar = {},
        content = { padding -> ProfileContent(padding, navController) },
        bottomBar = {})
}