package com.edival.recioblog.presentation.screens.posts_edit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
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
import com.edival.recioblog.presentation.screens.posts_edit.PostEditViewModel
import com.edival.recioblog.presentation.ui.theme.primaryColor
import com.edival.recioblog.presentation.utils.ShowImage

@Composable
fun PostsEditContent(paddingValues: PaddingValues, viewModel: PostEditViewModel = hiltViewModel()) {
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
        val (box, img, column) = createRefs()
        val (btnRadio, txtRadio, iconRadio) = createRefs()
        val topBox = createGuidelineFromTop(0.3f)
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
            .clickable { viewModel.stateDialog.value = true }
            .constrainAs(img) {
                top.linkTo(box.top)
                bottom.linkTo(box.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }, url = viewModel.state.imgUrl, iconId = R.drawable.outline_image_search
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(all = dimensionResource(id = R.dimen.padding_default))
                .constrainAs(column) {
                    top.linkTo(topBox)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                value = state.name,
                onValueChange = { viewModel.onNameInput(it) },
                validateField = { viewModel.validateName(ctx) },
                label = stringResource(id = R.string.name_of_the_game),
                icon = Icons.Outlined.Edit,
                keyboardType = KeyboardType.Text,
                errMsg = viewModel.nameErrMsg
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                value = state.description,
                onValueChange = { viewModel.onDescriptionInput(it) },
                validateField = { viewModel.validateDesc(ctx) },
                label = stringResource(id = R.string.description),
                icon = Icons.Outlined.List,
                keyboardType = KeyboardType.Text,
                errMsg = viewModel.descErrMsg
            )
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                text = stringResource(id = R.string.categories),
                fontWeight = FontWeight.Bold
            )
            viewModel.radioOptions.forEach { option ->
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min))
                        .selectable(selected = (stringResource(id = option.category) == state.category),
                            onClick = { viewModel.onCategoryInput(ctx.getString(option.category)) })
                ) {
                    RadioButton(modifier = Modifier.constrainAs(btnRadio) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(txtRadio.start)
                    },
                        selected = (stringResource(id = option.category) == state.category),
                        onClick = { viewModel.onCategoryInput(ctx.getString(option.category)) })
                    Text(
                        modifier = Modifier.constrainAs(txtRadio) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(btnRadio.end)
                            end.linkTo(iconRadio.start)
                        }, text = stringResource(id = option.category)
                    )
                    Icon(
                        modifier = Modifier.constrainAs(iconRadio) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(txtRadio.end)
                            end.linkTo(parent.end)
                        }, painter = painterResource(id = option.image), contentDescription = null
                    )
                }
            }
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                textId = R.string.update,
                onClick = { viewModel.updatePost() },
                icon = R.drawable.outline_edit,
                enabled = viewModel.isEnabledEditPostButton
            )
        }
    }
}