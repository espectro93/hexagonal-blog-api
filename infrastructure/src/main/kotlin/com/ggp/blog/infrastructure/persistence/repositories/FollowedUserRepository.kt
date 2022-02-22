package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.user.FollowedUser
import com.ggp.blog.domain.core.user.FollowedUserId
import com.ggp.blog.domain.core.user.FolloweeId
import com.ggp.blog.domain.core.user.UserId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface FollowedUserRepository : ReactiveMongoRepository<FollowedUser, FollowedUserId> {
    fun findAllByFollowerId(followerId: UserId, pageable: Pageable): Flux<FollowedUser>
    fun countAllByFollowerId(userId: UserId): Int
    fun countAllByFolloweeId(followeeId: FolloweeId): Int
    fun deleteByFollowerIdAndFolloweeId(followerId: UserId, followeeId: FolloweeId)
}