package com.edival.recioblog.domain.use_cases.users

data class UsersUseCases(
    val create: Create,
    val update: Update,
    val uploadImage: UploadImage,
    val getUserById: GetUserById
)