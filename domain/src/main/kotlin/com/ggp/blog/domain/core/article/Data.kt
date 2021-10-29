package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.core.comment.Comment
import com.ggp.blog.domain.core.shared.BaseEntity
import java.time.Instant

data class Slug(val value: String)
data class Title(val value: String)
data class Description(val value: String)
data class Body(val value: String)
data class Tag(val value: String)

data class Article(
        override var id: String?,
        val slug: Slug,
        val title: Title,
        val description: Description,
        val body: Body,
        val tags: Set<Tag>,
        val comments: List<Comment>,
        override var updatedAt: Instant,
        override var createdAt: Instant
) : BaseEntity {
    fun addComment(comment: Comment) = copy(comments = comments.plus(comment))
    fun deleteComment(comment: Comment) = copy(comments = comments.minus(comment))
}
