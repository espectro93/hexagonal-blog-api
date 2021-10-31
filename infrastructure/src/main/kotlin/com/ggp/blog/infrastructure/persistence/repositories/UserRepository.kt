package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.Username
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface UserRepository : ReactiveMongoRepository<User, String> {
    fun findByUsername(username: Username) : Mono<User>
}