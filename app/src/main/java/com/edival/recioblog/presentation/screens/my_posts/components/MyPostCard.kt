package com.edival.recioblog.presentation.screens.my_posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.presentation.navigation.DetailsScreen
import com.edival.recioblog.presentation.screens.my_posts.MyPostViewModel

@Composable
fun MyPostCard(
    navController: NavHostController, post: Post, viewModel: MyPostViewModel = hiltViewModel()
) {
    Card(modifier = Modifier
        .padding(all = dimensionResource(id = R.dimen.padding_default))
        .clickable { navController.navigate(route = DetailsScreen.DetailPost.sendPost(post.toJson())) }) {
        Column {
            post.imgUrl?.let { url ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.icon_big_size))
                        .padding(bottom = dimensionResource(id = R.dimen.padding_min)),
                    model = url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_min),
                    vertical = dimensionResource(id = R.dimen.padding_ultra_min)
                ), text = post.name ?: stringResource(id = R.string.unknown_post), maxLines = 1
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_min),
                    vertical = dimensionResource(id = R.dimen.padding_ultra_min)
                ),
                text = post.description ?: stringResource(id = R.string.unknown_description),
                fontStyle = FontStyle.Italic,
                maxLines = 1
            )
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = dimensionResource(id = R.dimen.padding_ultra_min))
            ) {
                val (iconEdit, iconDelete) = createRefs()
                IconButton(modifier = Modifier.constrainAs(iconEdit) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(iconDelete.start)
                },
                    onClick = { navController.navigate(route = DetailsScreen.PostEdit.sendPost(post.toJson())) }) {
                    Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                }
                IconButton(modifier = Modifier.constrainAs(iconDelete) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(iconEdit.end)
                    end.linkTo(parent.end)
                }, onClick = { post.id?.let { id -> viewModel.deletePost(id, post.imgUrl) } }) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                }
            }
        }
    }
}