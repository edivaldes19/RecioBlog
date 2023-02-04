package com.edival.recioblog.presentation.screens.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultButton
import com.edival.recioblog.presentation.components.DefaultTextField
import com.edival.recioblog.presentation.components.PasswordTextField
import com.edival.recioblog.presentation.ui.theme.primaryColor

@Composable
fun SignUpContent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (box, card) = createRefs()
        val topBox = createGuidelineFromTop(0.35f)
        val topCard = createGuidelineFromTop(0.25f)
        val bottomCard = createGuidelineFromTop(0.90f)
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        Box(modifier = Modifier
            .background(primaryColor)
            .constrainAs(box) {
                top.linkTo(parent.top)
                bottom.linkTo(topBox)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .padding(all = dimensionResource(id = R.dimen.padding_max))
                        .size(dimensionResource(id = R.dimen.icon_big_size)),
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Card(modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_default),
                end = dimensionResource(id = R.dimen.padding_default)
            )
            .constrainAs(card) {
                top.linkTo(topCard)
                bottom.linkTo(bottomCard)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(all = dimensionResource(id = R.dimen.padding_default))
            ) {
                Text(
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    text = stringResource(id = R.string.registration_form),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    text = stringResource(id = R.string.complete_form)
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = username,
                    onValueChange = { username = it },
                    label = stringResource(id = R.string.username),
                    icon = Icons.Outlined.Person,
                    keyboardType = KeyboardType.Text
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = email,
                    onValueChange = { email = it },
                    label = stringResource(id = R.string.email),
                    icon = Icons.Outlined.Email,
                    keyboardType = KeyboardType.Email
                )
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.password)
                )
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = stringResource(id = R.string.confirm_password)
                )
                DefaultButton(text = stringResource(id = R.string.register), onClick = {})
            }
        }
    }
}