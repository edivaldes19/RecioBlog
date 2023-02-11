package com.edival.recioblog.domain.use_cases.posts

data class PostsUseCases(
    val create: CreatePost,
    val getPosts: GetPosts,
    val getPostsByUserId: GetPostsByUserId,
    val deletePost: DeletePost,
    val updatePost: UpdatePost,
    val likePost: LikePost,
    val unlikePost: UnlikePost,
)