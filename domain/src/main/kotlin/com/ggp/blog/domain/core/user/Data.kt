package com.ggp.blog.domain.core.user

import com.ggp.blog.domain.core.article.Slug
import com.ggp.blog.domain.core.shared.BaseEntity
import com.ggp.blog.domain.core.shared.CreatedDate
import com.ggp.blog.domain.core.shared.Identifiable
import com.ggp.blog.domain.core.shared.UpdatedDate

data class UserId(override val value: String) : Identifiable
data class Username(val value: String)
data class Email(val value: String)
data class Bio(val value: String)
data class Image(val value: String)

data class User(
    override var id: UserId?,
    val username: Username,
    val email: Email,
    val profile: Profile,
    override var updatedAt: UpdatedDate? = null,
    override var createdAt: CreatedDate? = null
) : BaseEntity<UserId> {
    fun followUser(userId: UserId): FollowedUser {
        if (this.id != null) {
            return FollowedUser(
                followerId = this.id!!,
                followeeId = FolloweeId(userId.value)
            )
        }
        throw Exception("Inconsistent state! Following an user without an id is not permitted")
    }

    fun favorArticle(slug: Slug): FavoredArticle {
        if (this.id != null) {
            return FavoredArticle(
                userId = this.id!!,
                slug = slug
            )
        }
        throw Exception("Inconsistent state! Favoring an article without an id is not permitted")
    }
}

data class Profile(
    val bio: Bio,
    val image: Image
)

data class FollowedUserId(override val value: String) : Identifiable
data class FolloweeId(val value: String)

data class FollowedUser(
    override var id: FollowedUserId? = null,
    val followerId: UserId,
    val followeeId: FolloweeId,
    override var updatedAt: UpdatedDate? = null,
    override var createdAt: CreatedDate? = null
) : BaseEntity<FollowedUserId>

data class FavoredArticleId(override val value: String) : Identifiable

data class FavoredArticle(
    override var id: FavoredArticleId? = null,
    val userId: UserId,
    val slug: Slug,
    override var updatedAt: UpdatedDate? = null,
    override var createdAt: CreatedDate? = null
) : BaseEntity<FavoredArticleId>
