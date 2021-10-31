package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId
import kotlinx.coroutines.flow.Flow

interface LoadFollowedUser {
    suspend fun loadAllFollowedUsersPaged(userId: UserId, page: Int, size: Int): Flow<User>
    suspend fun loadFollowedCount(userId: UserId): Int
    suspend fun loadFollowersCount(userId: UserId): Int
}
