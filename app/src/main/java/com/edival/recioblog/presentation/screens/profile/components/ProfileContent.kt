package com.edival.recioblog.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.edival.recioblog.R
import com.edival.recioblog.presentation.MainActivity
import com.edival.recioblog.presentation.components.DefaultButton
import com.edival.recioblog.presentation.navigation.DetailsScreen
import com.edival.recioblog.presentation.screens.profile.ProfileViewModel
import com.edival.recioblog.presentation.ui.theme.errorRed
import com.edival.recioblog.presentation.ui.theme.primaryColor
import com.edival.recioblog.presentation.utils.ShowImage

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (box, txtWelcome, imgUser, column) = createRefs()
        val topBox = createGuidelineFromTop(0.3f)
        val topInfo = createGuidelineFromTop(0.4f)
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
        Text(
            modifier = Modifier.constrainAs(txtWelcome) {
                top.linkTo(box.top)
                bottom.linkTo(imgUser.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = stringResource(id = R.string.welcome),
            color = Color.White,
            fontSize = integerResource(id = R.integer.font_mega).sp,
            fontWeight = FontWeight.Bold
        )
        ShowImage(modifier = Modifier
            .size(dimensionResource(id = R.dimen.icon_big_size))
            .clip(CircleShape)
            .clickable { }
            .constrainAs(imgUser) {
                top.linkTo(topBox)
                bottom.linkTo(topBox)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, url = viewModel.userData.imgUrl, iconId = R.drawable.outline_person
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(all = dimensionResource(id = R.dimen.padding_default))
                .constrainAs(column) {
                    top.linkTo(topInfo)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                text = viewModel.userData.username ?: stringResource(id = R.string.unknown_user),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                text = viewModel.userData.email ?: stringResource(id = R.string.unknown_email),
                fontStyle = FontStyle.Italic
            )
            DefaultButton(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                textId = R.string.edit_profile,
                onClick = { navController.navigate(DetailsScreen.ProfileEdit.sendUser(viewModel.userData.toJson())) },
                icon = R.drawable.outline_edit,
                enabled = true
            )
            DefaultButton(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                textId = R.string.sign_off,
                onClick = {
                    viewModel.logOut()
                    activity?.let { act ->
                        act.finish()
                        act.startActivity(Intent(activity, MainActivity::class.java))
                    }
                },
                color = errorRed,
                icon = R.drawable.outline_logout,
                enabled = true
            )
        }
    }
}