package com.ggp.blog.infrastructure.persistence.model

import com.ggp.blog.domain.core.user.*
import java.time.Instant

data class PersistableArticle(
        override var id: String?,
        val slug: String,
        val title: String,
        val description: String,
        val body: String,
        val tags: Set<String>,
        override var createdAt: Instant?,
        override var updatedAt: Instant?
) : PersistableEntity

data class PersistableComment(
        override var id: String?,
        val slug: String,
        val parentId: String?,
        val body: String,
        override var createdAt: Instant?,
        override var updatedAt: Instant?
) : PersistableEntity

data class PersistableUser(
        override var id: String?,
        val profile: PersistableProfile,
        val email: String,
        val username: String,
        override var createdAt: Instant?,
        override var updatedAt: Instant?
) : PersistableEntity {
    companion object {
        fun toDomain(persistableUser: PersistableUser, followedCount: Int, followersCount: Int): User {
            return User(
                    id = UserId(persistableUser.id!!),
                    email = Email(persistableUser.email),
                    username = Username(persistableUser.username),
                    profile = PersistableProfile.toDomain(persistableUser.profile, followedCount = followedCount, followersCount = followersCount)
            )
        }
    }
}

data class PersistableProfile(
        val userId: String,
        val bio: String,
        val image: String
) {
    companion object {
        fun toDomain(persistableUserProfile: PersistableProfile, followedCount: Int, followersCount: Int): Profile {
            return Profile(
                    bio = Bio(persistableUserProfile.bio),
                    image = Image(persistableUserProfile.image),
                    followedCount = FollowedCount(followedCount),
                    followersCount = FollowersCount(followersCount)
            )
        }
    }
}

data class PersistableFollowedUser(
        override var id: String?,
        val userId: String,
        val followedUserId: String,
        override var createdAt: Instant?,
        override var updatedAt: Instant?
) : PersistableEntity

data class PersistableFavoredArticle(
        override var id: String?,
        val userId: String,
        val articleId: String,
        override var createdAt: Instant?,
        override var updatedAt: Instant?
) : PersistableEntity