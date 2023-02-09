package com.edival.recioblog.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.edival.recioblog.R
import com.edival.recioblog.presentation.ui.theme.errorRed

@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String,
    icon: ImageVector,
    errMsg: String? = null
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField()
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = { Text(text = label) },
            leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
            isError = errMsg != null
        )
        errMsg?.let {
            Text(
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_min)),
                text = it,
                fontSize = integerResource(id = R.integer.font_small).sp,
                color = errorRed
            )
        }
    }
}