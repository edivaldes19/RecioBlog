package com.edival.recioblog.domain.use_cases.users

import com.edival.recioblog.domain.model.Response
import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: User): Response<Boolean> = repository.create(user)
}