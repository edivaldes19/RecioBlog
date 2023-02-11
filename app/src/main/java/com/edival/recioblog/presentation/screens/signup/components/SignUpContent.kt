package com.edival.recioblog.presentation.screens.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultBox
import com.edival.recioblog.presentation.components.DefaultButton
import com.edival.recioblog.presentation.components.DefaultTextField
import com.edival.recioblog.presentation.components.PasswordTextField
import com.edival.recioblog.presentation.screens.signup.SignUpViewModel
import com.edival.recioblog.presentation.ui.theme.primaryColor

@Composable
fun SignUpContent(paddingValues: PaddingValues, viewModel: SignUpViewModel = hiltViewModel()) {
    val state = viewModel.state
    val ctx = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (box, card) = createRefs()
        val topBox = createGuidelineFromTop(0.35f)
        val topCard = createGuidelineFromTop(0.25f)
        val bottomCard = createGuidelineFromTop(0.90f)
        DefaultBox(
            modifier = Modifier
                .background(primaryColor)
                .constrainAs(box) {
                    top.linkTo(parent.top)
                    bottom.linkTo(topBox)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, iconId = R.drawable.outline_person_add_alt
        )
        Card(modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.padding_default))
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
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    validateField = { viewModel.validateUsername(ctx) },
                    keyboardType = KeyboardType.Text,
                    label = stringResource(id = R.string.username),
                    icon = Icons.Outlined.Person,
                    errMsg = viewModel.usernameErrMsg
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    validateField = { viewModel.validateEmail(ctx) },
                    label = stringResource(id = R.string.email),
                    icon = Icons.Outlined.Email,
                    keyboardType = KeyboardType.Email,
                    errMsg = viewModel.emailErrMsg
                )
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordInput(it) },
                    validateField = { viewModel.validatePassword(ctx) },
                    label = stringResource(id = R.string.password),
                    errMsg = viewModel.passwordErrMsg
                )
                PasswordTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = state.confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordInput(it) },
                    validateField = { viewModel.validateBothPasswords(ctx) },
                    label = stringResource(id = R.string.confirm_password),
                    errMsg = viewModel.confirmPasswordErrMsg
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_min),
                            bottom = dimensionResource(id = R.dimen.padding_ultra_min)
                        ),
                    textId = R.string.register,
                    onClick = { viewModel.signUp() },
                    icon = R.drawable.outline_person_add_alt,
                    enabled = viewModel.isEnabledSignUpButton
                )
            }
        }
    }
}