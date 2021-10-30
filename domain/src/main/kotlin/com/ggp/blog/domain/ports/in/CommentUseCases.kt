package com.ggp.blog.domain.ports.`in`

import com.ggp.blog.domain.core.article.Comment
import com.ggp.blog.domain.core.article.CommentId
import com.ggp.blog.domain.core.article.Slug

interface CreateCommentUseCase {
    suspend fun create(comment: Comment): Comment
}

interface GetCommentsUseCase {
    suspend fun getBySlug(slug: Slug): Comment?
}

interface DeleteCommentUseCase {
    suspend fun deleteBy(commentId: CommentId)
}