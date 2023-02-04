package com.edival.recioblog.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.edival.recioblog.R

@Composable
fun DefaultBox(
    modifier: Modifier,
    @DrawableRes iconId: Int,
    @StringRes contentId: Int? = null,
    @StringRes textId: Int? = null,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.padding_max))
                    .size(size = dimensionResource(id = R.dimen.icon_big_size)),
                painter = painterResource(id = iconId),
                contentDescription = contentId?.let { stringResource(id = it) },
                tint = Color.White
            )
            textId?.let {
                Text(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_max)),
                    text = stringResource(id = it),
                    color = Color.White,
                    fontSize = integerResource(id = R.integer.font_mega).sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}