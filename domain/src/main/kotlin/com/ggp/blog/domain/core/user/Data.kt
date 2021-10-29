package com.ggp.blog.domain.core.user

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.shared.BaseEntity

data class Email(val value: String)

data class User(
        override var id: String?,
        val profile: UserProfile?,
        val email: Email,
        val followedUsers: Set<User>,
        val favoredArticles: Set<Article>
) : BaseEntity {
    fun createProfile() = copy(profile = UserProfile("123"))
    fun deleteProfile() = copy(profile = null)
    fun followUser(user: User) = copy(followedUsers = followedUsers.plus(user))
    fun unfollowUser(user: User) = copy(followedUsers = followedUsers.minus(user))
    fun favorArticle(article: Article) = copy(favoredArticles = favoredArticles.plus(article))
    fun disfavorArticle(article: Article) = copy(favoredArticles = favoredArticles.minus(article))
}

data class UserProfile(
        val userId: String
)