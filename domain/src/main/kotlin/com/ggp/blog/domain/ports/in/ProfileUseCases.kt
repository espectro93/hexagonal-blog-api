package com.ggp.blog.domain.ports.`in`

import com.ggp.blog.domain.core.user.Profile
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.core.user.Username

interface GetUserProfileUseCase {
    suspend fun getBy(username: Username): Profile?
}

interface FollowUserUseCase {
    suspend fun followBy(followerId: UserId, followeeUsername: Username)
}

interface UnFollowUserUseCase {
    suspend fun unfollowBy(followerId: UserId, followeeUsername: Username)
}