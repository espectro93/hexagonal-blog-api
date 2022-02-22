package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.user.FollowedUser
import com.ggp.blog.domain.core.user.FolloweeId
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.ports.out.DeleteFollowedUser
import com.ggp.blog.domain.ports.out.LoadFollowedUser
import com.ggp.blog.domain.ports.out.StoreFollowedUser
import com.ggp.blog.infrastructure.persistence.repositories.FollowedUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class FollowedUserStorageAdapter(
    private val followedUserRepository: FollowedUserRepository
) : LoadFollowedUser, StoreFollowedUser, DeleteFollowedUser {
    override fun loadAllFollowedUsersPaged(userId: UserId, page: Int, size: Int): Flow<FollowedUser> {
        return followedUserRepository.findAllByFollowerId(userId, PageRequest.of(page, size))
            .asFlow()
    }

    override suspend fun loadFollowedCount(userId: UserId): Int {
        return followedUserRepository.countAllByFollowerId(userId)
    }

    override suspend fun loadFollowersCount(userId: UserId): Int {
        return followedUserRepository.countAllByFolloweeId(FolloweeId(userId.value))
    }

    override suspend fun store(followedUser: FollowedUser) {
        followedUserRepository.save(followedUser)
    }

    override suspend fun deleteBy(followerId: UserId, followeeId: FolloweeId) {
        followedUserRepository.deleteByFollowerIdAndFolloweeId(followerId, followeeId)
    }
}