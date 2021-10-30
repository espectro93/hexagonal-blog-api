package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.user.User
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.core.user.Username

interface LoadUser {
    suspend fun loadById(userId: UserId): User?
    suspend fun loadByUsername(username: Username): User?
}

interface StoreUser {
    suspend fun store(user: User): User
}

interface DeleteUser {
    suspend fun delete(userId: UserId)
}