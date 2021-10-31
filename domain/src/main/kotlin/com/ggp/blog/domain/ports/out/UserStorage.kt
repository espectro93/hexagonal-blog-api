package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.core.user.Username
import kotlinx.coroutines.flow.Flow

interface LoadUser {
    suspend fun loadBy(userId: UserId): User?
    suspend fun loadBy(username: Username): User?
}

interface StoreUser {
    suspend fun store(user: User): User
}

interface LoadFollowedUser {
    suspend fun loadAllFollowedPaged(userId: UserId): Flow<User>
    suspend fun loadFollowedCount(userId: UserId): Int
    suspend fun loadFollowersCount(userId: UserId): Int
}