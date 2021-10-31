package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.core.user.Username
import com.ggp.blog.domain.ports.out.LoadFollowedUser
import com.ggp.blog.domain.ports.out.LoadUser
import com.ggp.blog.domain.ports.out.StoreUser
import com.ggp.blog.infrastructure.persistence.model.PersistableUser
import com.ggp.blog.infrastructure.persistence.repositories.FollowedUserRepository
import com.ggp.blog.infrastructure.persistence.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitSingle

class UserStorageAdapter(private val userRepository: UserRepository,
                         private val followedUserRepository: FollowedUserRepository) : LoadUser, StoreUser, LoadFollowedUser {
    override suspend fun loadBy(userId: UserId): User? {
        return userRepository.findById(userId.value).awaitSingle()?.let { PersistableUser.toDomain(it) }
    }

    override suspend fun loadBy(username: Username): User? {
        return userRepository.findByUsername(username.value)
                .awaitSingle()?.let {
                    PersistableUser.toDomain(it)
                }
    }

    override suspend fun store(user: User): User {
        return userRepository.save(PersistableUser.fromDomain(user))
                .awaitSingle()
                .let { PersistableUser.toDomain(it) }
    }

    override suspend fun loadAllFollowedPaged(userId: UserId): Flow<User> {
        return followedUserRepository.
    }

    override suspend fun loadFollowedCount(userId: UserId): Int {
        TODO("Not yet implemented")
    }

    override suspend fun loadFollowersCount(userId: UserId): Int {
        TODO("Not yet implemented")
    }

}