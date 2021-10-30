package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.infrastructure.persistence.model.PersistableUser
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<PersistableUser, String> {
    fun findByUsername(username: String) : Mono<PersistableUser>
}