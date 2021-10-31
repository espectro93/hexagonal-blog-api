package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.article.Comment
import com.ggp.blog.domain.core.article.CommentId
import com.ggp.blog.domain.core.article.ParentCommentId
import com.ggp.blog.domain.core.article.Slug
import kotlinx.coroutines.flow.Flow

interface LoadComment {
    suspend fun loadBy(commentId: CommentId): Comment?
}

interface LoadAllCommentsForArticle {
    suspend fun loadAllBy(slug: Slug): Flow<Comment>
}

interface LoadAllCommentsForParentComment {
    suspend fun loadAllBy(parentCommentId: ParentCommentId): Flow<Comment>
}

interface StoreComment {
    suspend fun store(comment: Comment): Comment
}

interface DeleteComment {
    suspend fun deleteBy(commentId: CommentId)
}