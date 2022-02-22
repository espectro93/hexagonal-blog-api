package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.ParentPostCommentId
import com.ggp.blog.domain.core.article.PostComment
import com.ggp.blog.domain.core.article.PostCommentId
import com.ggp.blog.domain.core.article.Slug
import com.ggp.blog.domain.ports.out.*
import com.ggp.blog.infrastructure.persistence.repositories.PostCommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PostCommentStorageAdapter(
    private val postCommentRepository: PostCommentRepository
) : LoadPostComment, LoadAllPostCommentsForArticle, LoadAllPostCommentsForParentPostComment, StorePostComment,
    DeletePostComment {
    override suspend fun loadBy(id: PostCommentId): PostComment? {
        return postCommentRepository.findById(id).awaitSingleOrNull()
    }

    override fun loadAllBy(slug: Slug, page: Int, size: Int): Flow<PostComment> {
        return postCommentRepository.findAllBySlug(slug, PageRequest.of(page, size)).asFlow()
    }

    override fun loadAllBy(parentPostCommentId: ParentPostCommentId, page: Int, size: Int): Flow<PostComment> {
        return postCommentRepository.findAllByParentPostCommentId(parentPostCommentId, PageRequest.of(page, size))
            .asFlow()
    }

    override suspend fun loadBy(slug: Slug): PostComment? {
        return postCommentRepository.findBySlug(slug)
    }

    override suspend fun store(postComment: PostComment): PostComment {
        return postCommentRepository.save(postComment).awaitSingle()
    }

    override suspend fun deleteBy(postCommentId: PostCommentId) {
        postCommentRepository.deleteById(postCommentId)
    }

}