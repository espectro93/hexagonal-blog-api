package com.ggp.blog.domain.core.comment

import com.ggp.blog.domain.core.article.Body
import com.ggp.blog.domain.core.shared.BaseEntity
import java.time.Instant


data class Comment(
        override var id: String?,
        val articleId: String,
        val userId: String,
        val parentId: String?,
        val body: Body,
        val comments: List<Comment>,
        val createdAt: Instant,
        val updatedAt: Instant
) : BaseEntity {
    fun addComment(comment: Comment) = copy(comments = comments.plus(comment))
}