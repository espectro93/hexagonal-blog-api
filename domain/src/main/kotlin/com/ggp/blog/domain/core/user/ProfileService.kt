package com.ggp.blog.domain.core.user

import com.ggp.blog.domain.ports.`in`.FollowUserUseCase
import com.ggp.blog.domain.ports.`in`.GetUserProfileUseCase
import com.ggp.blog.domain.ports.`in`.UnFollowUserUseCase
import com.ggp.blog.domain.ports.out.LoadUser
import javax.annotation.Resource

@Resource
class ProfileService(private val loadUser: LoadUser) : GetUserProfileUseCase, FollowUserUseCase, UnFollowUserUseCase {
    override suspend fun getBy(username: Username): Profile? {
        return loadUser.loadByUsername(username)?.profile
    }

    override suspend fun followBy(username: Username) {
        TODO("Not yet implemented")
    }

    override suspend fun unfollowBy(username: Username) {
        TODO("Not yet implemented")
    }

}