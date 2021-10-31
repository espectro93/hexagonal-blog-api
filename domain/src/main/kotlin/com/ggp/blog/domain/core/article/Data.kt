package com.ggp.blog.domain.core.article

import java.time.Instant

data class ArticleId(val value: String)
data class Slug(val value: String)
data class Title(val value: String)
data class Description(val value: String)
data class Body(val value: String)
data class Tag(val value: String)
data class Author(val value: String)

data class Article(
        var id: ArticleId?,
        val author: Author,
        val slug: Slug,
        val title: Title,
        val description: Description,
        val body: Body,
        val tags: Set<Tag>,
        val updatedAt: Instant,
        val createdAt: Instant
) {
    private lateinit var comments: Set<Comment>

    fun addComment(comment: Comment) {
        if (this::comments.isInitialized) comments.plus(comment)
    }

    fun deleteComment(comment: Comment) {
        if (this::comments.isInitialized) comments.minus(comment)
    }
}

data class CommentId(val value: String)
data class ParentCommentId(val value: String)

data class Comment(
        var id: CommentId?,
        val parentId: ParentCommentId?,
        val userId: String,
        val body: Body,
        val comments: List<Comment>,
        val updatedAt: Instant,
        val createdAt: Instant
) {
    fun addComment(comment: Comment) = copy(comments = comments.plus(comment))
}