package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.ports.`in`.CreateCommentUseCase
import com.ggp.blog.domain.ports.`in`.DeleteCommentUseCase
import com.ggp.blog.domain.ports.`in`.GetCommentsUseCase
import com.ggp.blog.domain.ports.out.*
import javax.annotation.Resource

@Resource
class CommentService(
        private val loadComment: LoadPostComment,
        private val loadAllCommentsForArticle: LoadAllPostCommentsForArticle,
        private val loadAllCommentsForParentComment: LoadAllPostCommentsForParentPostComment,
        private val storeComment: StorePostComment,
        private val deleteComment: DeletePostComment
) : CreateCommentUseCase, GetCommentsUseCase, DeleteCommentUseCase{
    override suspend fun create(comment: PostComment): PostComment {
        TODO("Not yet implemented")
    }

    override suspend fun getBySlug(slug: Slug): PostComment? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBy(postCommentId: PostCommentId) {
        TODO("Not yet implemented")
    }

}