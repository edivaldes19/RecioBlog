package com.edival.recioblog.domain.use_cases.users

import com.edival.recioblog.domain.model.User
import com.edival.recioblog.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository) {
    operator fun invoke(id: String): Flow<User> = repository.getUserById(id)
}