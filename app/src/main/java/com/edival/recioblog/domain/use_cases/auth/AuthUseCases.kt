package com.edival.recioblog.domain.use_cases.auth

data class AuthUseCases(
    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val signUp: SignUp,
    val resetPassword: ResetPassword,
    val signOff: SignOff
)