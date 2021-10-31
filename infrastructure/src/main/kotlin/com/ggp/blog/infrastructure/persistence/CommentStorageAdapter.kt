package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.Comment
import com.ggp.blog.domain.core.article.CommentId
import com.ggp.blog.domain.core.article.ParentCommentId
import com.ggp.blog.domain.core.article.Slug
import com.ggp.blog.domain.ports.out.*
import kotlinx.coroutines.flow.Flow

class CommentStorageAdapter : LoadComment, LoadAllCommentsForArticle, LoadAllCommentsForParentComment, StoreComment, DeleteComment {
    override suspend fun loadBy(commentId: CommentId): Comment? {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllBy(slug: Slug): Flow<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllBy(parentCommentId: ParentCommentId): Flow<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun store(comment: Comment): Comment {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBy(commentId: CommentId) {
        TODO("Not yet implemented")
    }

}