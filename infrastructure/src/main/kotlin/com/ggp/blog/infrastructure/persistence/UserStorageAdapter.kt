package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.core.user.Username
import com.ggp.blog.domain.ports.out.LoadUser
import com.ggp.blog.domain.ports.out.StoreUser
import com.ggp.blog.infrastructure.persistence.repositories.UserRepository
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull

class UserStorageAdapter(private val userRepository: UserRepository) : LoadUser, StoreUser {
    override suspend fun loadBy(userId: UserId): User? {
        return userRepository.findById(userId).awaitSingleOrNull()
    }

    override suspend fun loadBy(username: Username): User? {
        return userRepository.findByUsername(username).awaitSingleOrNull()
    }

    override suspend fun store(user: User): User {
        return userRepository.save(user).awaitSingle()
    }
}