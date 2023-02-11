package com.edival.recioblog.presentation.screens.profile_edit

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultTopBar
import com.edival.recioblog.presentation.screens.profile_edit.components.ProfileEditContent
import com.edival.recioblog.presentation.screens.profile_edit.components.Update

@Composable
fun ProfileEditScreen(navController: NavHostController) {
    Scaffold(topBar = {
        DefaultTopBar(
            title = stringResource(id = R.string.edit_profile),
            upAvailable = true,
            navController = navController
        )
    }, content = { padding -> ProfileEditContent(padding) }, bottomBar = {})
    Update(navController = navController)
}