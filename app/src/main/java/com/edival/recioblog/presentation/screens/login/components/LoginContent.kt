package com.edival.recioblog.presentation.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultButton
import com.edival.recioblog.presentation.components.DefaultTextField
import com.edival.recioblog.presentation.components.PasswordTextField
import com.edival.recioblog.presentation.ui.theme.primaryColor
import com.edival.recioblog.presentation.ui.theme.primaryLightColor

@Composable
fun LoginContent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (box, card) = createRefs()
        val topBox = createGuidelineFromTop(0.35f)
        val topCard = createGuidelineFromTop(0.25f)
        val bottomCard = createGuidelineFromTop(0.75f)
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
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
                    painter = painterResource(id = R.drawable.outline_videogame_asset),
                    contentDescription = stringResource(id = R.string.icon_app),
                    tint = Color.White
                )
                Text(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_max)),
                    text = stringResource(id = R.string.app_name),
                    color = Color.White,
                    fontSize = integerResource(id = R.integer.font_mega).sp,
                    fontWeight = FontWeight.Bold
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
                    text = stringResource(id = R.string.welcome),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    text = stringResource(id = R.string.sign_in_to_continue),
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
                Text(modifier = Modifier
                    .clickable {}
                    .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    text = stringResource(id = R.string.did_you_forget_your_password),
                    color = primaryLightColor,
                    textAlign = TextAlign.End)
                DefaultButton(text = stringResource(id = R.string.login), onClick = {})
            }
        }
    }
}