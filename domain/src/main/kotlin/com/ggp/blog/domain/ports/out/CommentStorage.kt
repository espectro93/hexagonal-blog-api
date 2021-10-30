package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.comment.Comment

interface LoadComment {
    suspend fun load(commentId: String): Comment?
}

interface LoadAllCommentsForArticle {
    suspend fun loadAllForArticle(articleId: String): List<Comment>
}

interface LoadAllCommentsForParentComment {
    suspend fun loadAllForParent(parentId: String): List<Comment>
}

interface StoreComment {
    suspend fun store(comment: Comment): Comment
}

interface DeleteComment {
    suspend fun delete(commentId: String)
}