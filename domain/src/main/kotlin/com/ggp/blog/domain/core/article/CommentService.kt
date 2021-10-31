package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.ports.`in`.CreateCommentUseCase
import com.ggp.blog.domain.ports.`in`.DeleteCommentUseCase
import com.ggp.blog.domain.ports.`in`.GetCommentsUseCase
import com.ggp.blog.domain.ports.out.*

class CommentService(
        private val loadComment: LoadComment,
        private val loadAllCommentsForArticle: LoadAllCommentsForArticle,
        private val loadAllCommentsForParentComment: LoadAllCommentsForParentComment,
        private val storeComment: StoreComment,
        private val deleteComment: DeleteComment
) : CreateCommentUseCase, GetCommentsUseCase, DeleteCommentUseCase{
    override fun create(comment: Comment) {
        TODO("Not yet implemented")
    }

    override fun getBySlug(slug: Slug) {
        TODO("Not yet implemented")
    }

    override fun deleteBy(postCommentId: PostCommentId) {
        TODO("Not yet implemented")
    }
}