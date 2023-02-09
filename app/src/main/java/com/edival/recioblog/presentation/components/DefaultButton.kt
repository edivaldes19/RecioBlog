package com.edival.recioblog.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.edival.recioblog.R
import com.edival.recioblog.presentation.ui.theme.primaryColor

@Composable
fun DefaultButton(
    modifier: Modifier,
    @StringRes textId: Int,
    onClick: () -> Unit,
    color: Color = primaryColor,
    @DrawableRes icon: Int? = null,
    enabled: Boolean = false
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = Color.White),
        enabled = enabled
    ) {
        icon?.let {
            Icon(painter = painterResource(id = it), contentDescription = null, tint = Color.White)
        }
        Text(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_ultra_min)),
            text = stringResource(id = textId)
        )
    }
}