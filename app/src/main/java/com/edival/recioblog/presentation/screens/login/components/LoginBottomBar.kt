package com.edival.recioblog.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.presentation.navigation.AppScreen
import com.edival.recioblog.presentation.ui.theme.primaryLightColor

@Composable
fun LoginBottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.padding_default)),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_ultra_min)),
            text = stringResource(id = R.string.not_have_account),
        )
        Text(modifier = Modifier
            .clickable { navController.navigate(route = AppScreen.SignUp.route) }
            .padding(all = dimensionResource(id = R.dimen.padding_ultra_min)),
            text = stringResource(id = R.string.sign_up_here),
            color = primaryLightColor,
            fontWeight = FontWeight.Bold)
    }
}