package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.ports.`in`.CreateCommentUseCase
import com.ggp.blog.domain.ports.`in`.DeleteCommentUseCase
import com.ggp.blog.domain.ports.`in`.GetCommentsUseCase
import com.ggp.blog.domain.ports.out.*
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val loadComment: LoadPostComment,
    private val loadAllCommentsForArticle: LoadAllPostCommentsForArticle,
    private val loadAllCommentsForParentComment: LoadAllPostCommentsForParentPostComment,
    private val storeComment: StorePostComment,
    private val deleteComment: DeletePostComment
) : CreateCommentUseCase, GetCommentsUseCase, DeleteCommentUseCase {
    override suspend fun create(comment: PostComment): PostComment {
        return storeComment.store(comment)
    }

    override suspend fun getBySlug(slug: Slug): PostComment? {
        return loadComment.loadBy(slug)
    }

    override suspend fun deleteBy(postCommentId: PostCommentId) {
        return deleteComment.deleteBy(postCommentId)
    }

}