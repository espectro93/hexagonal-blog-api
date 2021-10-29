package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.core.shared.BaseEntity
import java.time.Instant

data class Slug(val value: String)
data class Title(val value: String)
data class Description(val value: String)
data class Body(val value: String)

data class Article(
        override var id: String?,
        val slug: Slug,
        val title: Title,
        val description: Description,
        val body: Body,
        val tags: Set<String>,
        val comments: List<Comment>
) : BaseEntity {
    fun addComment() = copy(comments = listOf())
    fun deleteComment() = copy(comments = listOf())
}

data class Comment(
        val commentId: String,
        val articleId: String,
        val body: Body,
        val author: String, //Replace with MinimalAuthor or similar Object tuple like (id + string)
        val createdAt: Instant,
        val updatedAt: Instant
)