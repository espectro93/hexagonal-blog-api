package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.article.PostComment
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PostCommentRepository : ReactiveMongoRepository<PostComment, String> {
}