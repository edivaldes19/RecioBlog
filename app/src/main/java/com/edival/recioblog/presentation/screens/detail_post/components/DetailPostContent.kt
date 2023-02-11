package com.edival.recioblog.presentation.screens.detail_post.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.edival.recioblog.R
import com.edival.recioblog.presentation.screens.detail_post.DetailPostViewModel
import com.edival.recioblog.presentation.screens.posts.components.IconByCategory
import com.edival.recioblog.presentation.utils.ShowImage

@Composable
fun DetailPostContent(
    paddingValues: PaddingValues, viewModel: DetailPostViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        val (imgPost, column) = createRefs()
        val topImg = createGuidelineFromTop(0.3f)
        ShowImage(modifier = Modifier.constrainAs(imgPost) {
            top.linkTo(parent.top)
            bottom.linkTo(topImg)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.fillToConstraints
            width = Dimension.fillToConstraints
        }, url = viewModel.post.imgUrl, iconId = R.drawable.outline_hide_image)
        Column(modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.padding_default))
            .verticalScroll(rememberScrollState())
            .constrainAs(column) {
                top.linkTo(imgPost.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }) {
            viewModel.post.user?.let { userPost ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.padding_min))
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = dimensionResource(id = R.dimen.padding_min)),
                    ) {
                        val (imgUser, txtUsername, txtUserEmail) = createRefs()
                        ShowImage(
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.icon_small_size))
                                .constrainAs(imgUser) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(txtUsername.start)
                                }, url = userPost.imgUrl, iconId = R.drawable.outline_person
                        )
                        Text(
                            modifier = Modifier.constrainAs(txtUsername) {
                                top.linkTo(parent.top)
                                bottom.linkTo(txtUserEmail.top)
                                start.linkTo(imgUser.end)
                                end.linkTo(parent.end)
                            },
                            text = userPost.username ?: stringResource(id = R.string.unknown_user),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.constrainAs(txtUserEmail) {
                                top.linkTo(txtUsername.bottom)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(imgUser.end)
                                end.linkTo(parent.end)
                            }, text = userPost.email ?: stringResource(id = R.string.unknown_user)
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                text = viewModel.post.name ?: stringResource(id = R.string.unknown_post),
                fontWeight = FontWeight.Bold
            )
            IconByCategory(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                category = viewModel.post.category
            )
            Divider(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                text = stringResource(id = R.string.description),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_min)),
                text = viewModel.post.description
                    ?: stringResource(id = R.string.unknown_description)
            )
        }
    }
}