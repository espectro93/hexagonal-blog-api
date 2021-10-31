package com.ggp.blog.domain.ports.`in`

import com.ggp.blog.domain.core.article.PostComment
import com.ggp.blog.domain.core.article.PostCommentId
import com.ggp.blog.domain.core.article.Slug

interface CreateCommentUseCase {
    suspend fun create(comment: PostComment): PostComment
}

interface GetCommentsUseCase {
    suspend fun getBySlug(slug: Slug): PostComment?
}

interface DeleteCommentUseCase {
    suspend fun deleteBy(postCommentId: PostCommentId)
}