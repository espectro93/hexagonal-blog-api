package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.user.User

interface LoadUser {
    suspend fun load(userId: String): User?
}

interface StoreUser {
    suspend fun store(user: User): User
}

interface DeleteUser {
    suspend fun delete(userId: String)
}