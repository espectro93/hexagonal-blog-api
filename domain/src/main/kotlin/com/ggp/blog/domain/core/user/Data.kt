package com.ggp.blog.domain.core.user

import com.ggp.blog.domain.core.article.Article

data class UserId(val value: String)
data class Username(val value: String)
data class Email(val value: String)
data class Bio(val value: String)
data class Image(val value: String)
data class FollowedCount(val value: Int)
data class FollowersCount(val value: Int)

data class User(
        var id: UserId?,
        val username: Username,
        val email: Email,
        val profile: Profile
) {
    private lateinit var followedUsers: Set<User>
    private lateinit var favoredArticles: Set<Article>

    fun followUser(user: User) {
        if (this::followedUsers.isInitialized && user.id != this.id) followedUsers.plus(user)
    }

    fun unfollowUser(user: User) {
        if (this::followedUsers.isInitialized && user.id != this.id) followedUsers.minus(user)
    }

    fun favorArticle(article: Article) {
        if (this::favoredArticles.isInitialized) favoredArticles.plus(article)
    }

    fun disfavorArticle(article: Article) {
        if (this::favoredArticles.isInitialized) favoredArticles.plus(article)
    }
}

data class Profile(
        val bio: Bio,
        val image: Image,
        val followedCount: FollowedCount,
        val followersCount: FollowersCount
)