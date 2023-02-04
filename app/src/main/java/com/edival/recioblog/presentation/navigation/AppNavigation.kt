package com.edival.recioblog.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.edival.recioblog.presentation.screens.login.LoginScreen
import com.edival.recioblog.presentation.screens.signup.SignUpScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AppScreen.Login.route) {
        composable(route = AppScreen.Login.route) { LoginScreen(navController = navController) }
        composable(route = AppScreen.SignUp.route) { SignUpScreen(navController = navController) }
    }
}