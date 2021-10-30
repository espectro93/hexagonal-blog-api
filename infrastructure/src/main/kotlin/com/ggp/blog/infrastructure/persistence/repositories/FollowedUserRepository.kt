package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.infrastructure.persistence.model.PersistableFollowedUser
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface FollowedUserRepository : ReactiveMongoRepository<PersistableFollowedUser, String> {
    fun findByUserId(userId: String): Mono<Int>
    fun findByFollowedUserId(followedUserId: String): Mono<Int>
}