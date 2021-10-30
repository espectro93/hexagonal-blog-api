package com.ggp.blog.domain.ports.`in`

import com.ggp.blog.domain.core.user.Email
import com.ggp.blog.domain.core.user.Profile
import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId

interface CreateUserUseCase {
    suspend fun create(user: User): User
}

interface EditUserUseCase {
    suspend fun editBy(userId: UserId, email: Email?, profile: Profile?): User
}

interface GetUserUseCase {
    suspend fun getBy(userId: UserId): User?
}
