package com.ggp.blog.domain.core.user

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.article.Slug

//TODO: remove persistence layer, split aggregates and create factory methods on them for actions

data class UserId(val value: String)
data class Username(val value: String)
data class Email(val value: String)
data class Bio(val value: String)
data class Image(val value: String)
data class FolloweeId(val value: String)

data class User(
        var id: UserId?,
        val username: Username,
        val email: Email,
        val profile: Profile
) {
    fun follow(user: User): FollowedUser {
        //TODO: check non null for both ids
        return FollowedUser(
                id = this.id!!,
                followeeId = FolloweeId(user.id!!.value)
        )
    }

    fun favor(article: Article): FavoredArticle {
        return FavoredArticle(
                id = this.id!!,
                slug = article.slug
        )
    }
}

data class Profile(
        val bio: Bio,
        val image: Image
)

data class FollowedUser(
        val id: UserId,
        val followeeId: FolloweeId
)

data class FavoredArticle(
        val id: UserId,
        val slug: Slug
)