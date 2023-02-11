package com.edival.recioblog.presentation.screens.posts.components

import androidx.annotation.DrawableRes
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.edival.recioblog.R

@Composable
fun IconByCategory(modifier: Modifier, category: String?) {
    @DrawableRes val icon = when (category) {
        stringResource(id = R.string.pc) -> R.drawable.outline_computer
        stringResource(id = R.string.xbox) -> R.drawable.xbox_logo
        stringResource(id = R.string.ps4) -> R.drawable.play_station_logo
        stringResource(id = R.string.nintendo) -> R.drawable.nintendo_logo
        stringResource(id = R.string.mobile_phone) -> R.drawable.outline_phone_android
        else -> R.drawable.outline_info
    }
    Icon(modifier = modifier, painter = painterResource(id = icon), contentDescription = null)
}