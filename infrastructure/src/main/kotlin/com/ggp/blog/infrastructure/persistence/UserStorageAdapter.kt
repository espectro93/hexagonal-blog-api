package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.core.user.Username
import com.ggp.blog.domain.ports.out.DeleteUser
import com.ggp.blog.domain.ports.out.LoadUser
import com.ggp.blog.domain.ports.out.StoreUser
import com.ggp.blog.infrastructure.persistence.model.PersistableUser
import com.ggp.blog.infrastructure.persistence.repositories.FollowedUserRepository
import com.ggp.blog.infrastructure.persistence.repositories.UserRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle

class UserStorageAdapter(private val userRepository: UserRepository,
                         private val followedUserRepository: FollowedUserRepository) : LoadUser, StoreUser, DeleteUser {
    override suspend fun loadById(userId: UserId): User? {
        return userRepository.findById(userId.value)
                .zipWith(followedUserRepository.findByUserId(userId.value))
                .zipWith(followedUserRepository.findByFollowedUserId(userId.value))
                .awaitSingle()?.let { PersistableUser.toDomain(it.t1.t1, it.t1.t2, it.t2) }
    }

    override suspend fun loadByUsername(username: Username): User? {
        return userRepository.findByUsername(username.value)
                .awaitSingle()?.let {
                    PersistableUser.toDomain(it,
                            it.id?.let { followedUserId -> followedUserRepository.findByUserId(followedUserId).awaitSingle() }
                                    ?: 0,
                            it.id?.let { followersUserId -> followedUserRepository.findByFollowedUserId(followersUserId).awaitSingle() }
                                    ?: 0)
                }
    }

    override suspend fun store(user: User): User {
        TODO("Not yet implemented")
    }

    override suspend fun delete(userId: UserId) {
        TODO("Not yet implemented")
    }

}