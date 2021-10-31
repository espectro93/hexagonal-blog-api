package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.ports.out.LoadFollowedUser
import com.ggp.blog.infrastructure.persistence.repositories.FollowedUserRepository
import kotlinx.coroutines.flow.Flow

class FollowedUserStorageAdapter(
    private val userRepository: FollowedUserRepository, private val followedUserRepository: FollowedUserRepository
) : LoadFollowedUser {
    override suspend fun loadAllFollowedUsersPaged(userId: UserId, page: Int, size: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun loadFollowedCount(userId: UserId): Int {
        TODO("Not yet implemented")
    }

    override suspend fun loadFollowersCount(userId: UserId): Int {
        TODO("Not yet implemented")
    }
}