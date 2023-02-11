package com.edival.recioblog.presentation.screens.posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.edival.recioblog.R
import com.edival.recioblog.domain.model.Post
import com.edival.recioblog.presentation.navigation.DetailsScreen
import com.edival.recioblog.presentation.screens.posts.PostViewModel

@Composable
fun PostCard(
    navController: NavHostController, post: Post, viewModel: PostViewModel = hiltViewModel()
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
                ),
                text = post.user?.username ?: stringResource(id = R.string.unknown_user),
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
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
                val (iconLike, txtNumberLikes) = createRefs()
                viewModel.currentUser?.let { myId ->
                    if (post.likes.contains(myId)) {
                        IconButton(modifier = Modifier.constrainAs(iconLike) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(txtNumberLikes.start)
                        }, onClick = { post.id?.let { pId -> viewModel.unlike(pId, myId) } }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite_fill),
                                contentDescription = null
                            )
                        }
                    } else {
                        IconButton(modifier = Modifier.constrainAs(iconLike) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(txtNumberLikes.start)
                        }, onClick = { post.id?.let { pId -> viewModel.like(pId, myId) } }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite_border),
                                contentDescription = null
                            )
                        }
                    }
                }
                Text(
                    modifier = Modifier.constrainAs(txtNumberLikes) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(iconLike.end)
                        end.linkTo(parent.end)
                    }, text = "${post.likes.size}", maxLines = 1
                )
            }
        }
    }
}