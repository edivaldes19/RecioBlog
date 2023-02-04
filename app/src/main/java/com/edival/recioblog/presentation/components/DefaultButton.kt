package com.edival.recioblog.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.edival.recioblog.R
import com.edival.recioblog.presentation.ui.theme.primaryColor

@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit,
    color: Color = primaryColor,
    icon: ImageVector? = null,
    enabled: Boolean = false
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.padding_min),
                bottom = dimensionResource(id = R.dimen.padding_ultra_min)
            ), onClick = onClick, colors = ButtonDefaults.buttonColors(
            backgroundColor = color, contentColor = Color.White
        ), enabled = enabled
    ) {
        icon?.let { Icon(imageVector = it, contentDescription = null, tint = Color.White) }
        Text(
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_ultra_min)),
            text = text
        )
    }
}