package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.article.ParentPostCommentId
import com.ggp.blog.domain.core.article.PostComment
import com.ggp.blog.domain.core.article.PostCommentId
import com.ggp.blog.domain.core.article.Slug
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface PostCommentRepository : ReactiveMongoRepository<PostComment, PostCommentId> {
    fun findAllBySlug(slug: Slug, pageable: Pageable): Flux<PostComment>
    fun findAllByParentPostCommentId(parentPostCommentId: ParentPostCommentId, pageable: Pageable): Flux<PostComment>
    fun findBySlug(slug: Slug): PostComment?
}