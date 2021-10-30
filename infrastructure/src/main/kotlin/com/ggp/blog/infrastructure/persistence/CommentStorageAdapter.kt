package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.Comment
import com.ggp.blog.domain.ports.out.*

class CommentStorageAdapter : LoadComment, LoadAllCommentsForArticle, LoadAllCommentsForParentComment, StoreComment, DeleteComment {
    override suspend fun load(commentId: String): Comment? {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllForParent(parentId: String): List<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllForArticle(articleId: String): List<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun store(comment: Comment): Comment {
        TODO("Not yet implemented")
    }

    override suspend fun delete(commentId: String) {
        TODO("Not yet implemented")
    }
}