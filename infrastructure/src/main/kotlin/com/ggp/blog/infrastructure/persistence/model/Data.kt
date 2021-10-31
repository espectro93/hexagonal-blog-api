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
        override var updatedAt: Instant?
) : PersistableEntity

data class PersistableComment(
        override var id: String?,
        val slug: String,
        val parentId: String?,
        val body: String,
        override var updatedAt: Instant?
) : PersistableEntity

data class PersistableUser(
        override var id: String?,
        val profile: PersistableProfile,
        val email: String,
        val username: String,
        override var updatedAt: Instant? = Instant.now()
) : PersistableEntity {
    companion object {
        fun toDomain(persistableUser: PersistableUser): User {
            return User(
                    id = UserId(persistableUser.id!!),
                    email = Email(persistableUser.email),
                    username = Username(persistableUser.username),
                    profile = PersistableProfile.to(persistableUser.profile)
            )
        }
        fun fromDomain(user: User): PersistableUser {
            return PersistableUser(
                    id = user.id?.value,
                    email = user.email.value,
                    username = user.username.value,
                    profile = PersistableProfile.from(user.profile)
            )
        }
    }
}

data class PersistableProfile(
        val bio: String,
        val image: String
) {
    companion object {
        fun to(persistableUserProfile: PersistableProfile): Profile {
            return Profile(
                    bio = Bio(persistableUserProfile.bio),
                    image = Image(persistableUserProfile.image)
            )
        }
        fun from(profile: Profile): PersistableProfile {
            return PersistableProfile(
                    bio = profile.bio.value,
                    image = profile.image.value
            )
        }
    }
}

data class PersistableFollowedUser(
        override var id: String?,
        val userId: String,
        val followedUserId: String,
        override var updatedAt: Instant?
) : PersistableEntity

data class PersistableFavoredArticle(
        override var id: String?,
        val userId: String,
        val articleId: String,
        override var updatedAt: Instant?
) : PersistableEntity