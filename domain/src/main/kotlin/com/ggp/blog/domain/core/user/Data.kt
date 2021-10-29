package com.ggp.blog.domain.core.user

import com.ggp.blog.domain.core.shared.BaseEntity

data class User(
        override var id: String?,
        val profile: UserProfile?
) : BaseEntity {
    fun createProfile() = copy(profile = UserProfile("123"))
    fun deleteProfile() = copy(profile = null)
}

data class UserProfile(
        val userId: String
)