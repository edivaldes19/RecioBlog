package com.edival.recioblog.domain.use_cases.auth

import com.edival.recioblog.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUser
@Inject constructor(private val repository: AuthRepository) {
    operator fun invoke(): FirebaseUser? = repository.currentUser
}