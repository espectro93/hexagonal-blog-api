package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.infrastructure.persistence.model.PersistableFollowedUser
import kotlinx.coroutines.flow.Flow
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface FollowedUserRepository : ReactiveMongoRepository<PersistableFollowedUser, String> {
    fun findByUserId(userId: String): Flux<PersistableFollowedUser>
    fun findByFollowedUserId(followedUserId: String): Flux<PersistableFollowedUser>
}