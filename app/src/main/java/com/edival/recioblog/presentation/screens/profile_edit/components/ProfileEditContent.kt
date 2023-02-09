package com.edival.recioblog.presentation.screens.profile_edit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.recioblog.R
import com.edival.recioblog.presentation.components.DefaultButton
import com.edival.recioblog.presentation.components.DefaultTextField
import com.edival.recioblog.presentation.components.DialogCapturePicture
import com.edival.recioblog.presentation.screens.profile_edit.ProfileEditViewModel
import com.edival.recioblog.presentation.ui.theme.primaryColor
import com.edival.recioblog.presentation.utils.ShowImage

@Composable
fun ProfileEditContent(
    paddingValues: PaddingValues, viewModel: ProfileEditViewModel = hiltViewModel()
) {
    val state = viewModel.state
    viewModel.resultingActivityHandler.Handle()
    val ctx = LocalContext.current
    DialogCapturePicture(status = viewModel.stateDialog,
        takePhoto = { viewModel.getImage(ctx, false) },
        pickImage = { viewModel.getImage(ctx, true) })
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (box, img, card) = createRefs()
        val topBox = createGuidelineFromTop(0.35f)
        val topCard = createGuidelineFromTop(0.25f)
        val bottomCard = createGuidelineFromTop(0.75f)
        Box(modifier = Modifier
            .background(primaryColor)
            .constrainAs(box) {
                top.linkTo(parent.top)
                bottom.linkTo(topBox)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            })
        ShowImage(modifier = Modifier
            .size(dimensionResource(id = R.dimen.icon_big_size))
            .clip(CircleShape)
            .clickable { viewModel.stateDialog.value = true }
            .constrainAs(img) {
                top.linkTo(parent.top)
                bottom.linkTo(topCard)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, url = viewModel.state.imgUrl
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
                    text = stringResource(id = R.string.notice_about_profile_editing),
                    fontWeight = FontWeight.Bold
                )
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    validateField = { viewModel.validateUsername() },
                    keyboardType = KeyboardType.Text,
                    label = stringResource(id = R.string.username),
                    icon = Icons.Outlined.Person,
                    errMsg = viewModel.usernameErrMsg
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_min),
                            bottom = dimensionResource(id = R.dimen.padding_ultra_min)
                        ),
                    textId = R.string.edit_profile,
                    onClick = { viewModel.uploadImage() },
                    icon = R.drawable.outline_edit,
                    enabled = viewModel.isUsernameValid
                )
            }
        }
    }
}