package com.edival.recioblog.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.edival.recioblog.R
import com.edival.recioblog.presentation.ui.theme.errorRed

@Composable
fun PasswordTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    errMsg: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(text = label) },
            trailingIcon = {
                val image = if (passwordVisible) Icons.Outlined.Star else Icons.Outlined.Lock
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = null) },
            visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
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