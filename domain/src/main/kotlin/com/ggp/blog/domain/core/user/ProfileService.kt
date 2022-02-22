package com.ggp.blog.domain.core.user

import com.ggp.blog.domain.ports.`in`.FollowUserUseCase
import com.ggp.blog.domain.ports.`in`.GetUserProfileUseCase
import com.ggp.blog.domain.ports.`in`.UnFollowUserUseCase
import com.ggp.blog.domain.ports.out.DeleteFollowedUser
import com.ggp.blog.domain.ports.out.LoadUser
import com.ggp.blog.domain.ports.out.StoreFollowedUser
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val loadUser: LoadUser,
    private val storeFollowedUser: StoreFollowedUser,
    private val deleteFollowedUser: DeleteFollowedUser
) :
    GetUserProfileUseCase, FollowUserUseCase, UnFollowUserUseCase {
    override suspend fun getBy(username: Username): Profile? {
        return loadUser.loadBy(username)?.profile
    }

    override suspend fun followBy(followerId: UserId, followeeUsername: Username) {
        loadUser.loadBy(followeeUsername)
            ?.let { toFollow -> loadUser.loadBy(followerId)?.followUser(toFollow.id!!) }
            ?.let { storeFollowedUser.store(it) }
            ?: throw Exception("Error! Following $followeeUsername failed")
    }

    override suspend fun unfollowBy(followerId: UserId, followeeUsername: Username) {
        loadUser.loadBy(followeeUsername)
            ?.let { toUnfollow -> deleteFollowedUser.deleteBy(followerId, FolloweeId(toUnfollow.id!!.value)) }
            ?: throw Exception("Error! Unfollowing $followeeUsername failed")
    }
}