package com.edival.recioblog.domain.use_cases.auth

data class AuthUseCases(
    val getCurrentUser: GetCurrentUser, val login: Login, val logOut: LogOut, val signUp: SignUp
)