package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.user.FollowedUser
import com.ggp.blog.domain.core.user.FolloweeId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface FollowedUserRepository : ReactiveMongoRepository<FollowedUser, String> {
    fun findAllByFolloweeId(followeeId: FolloweeId): Flux<FollowedUser>
}