package com.edival.recioblog.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.edival.recioblog.presentation.ui.theme.primaryColor
import com.edival.recioblog.presentation.ui.theme.secondaryTextColor

@Composable
fun DefaultTopBar(
    isDarkMode: Boolean = false,
    title: String,
    upAvailable: Boolean = false,
    navController: NavHostController? = null
) {
    TopAppBar(elevation = 0.dp,
        backgroundColor = if (isDarkMode) secondaryTextColor else primaryColor,
        title = { Text(text = title) },
        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = { navController?.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        })
}