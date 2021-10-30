package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.infrastructure.persistence.model.PersistableComment
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CommentRepository : ReactiveMongoRepository<PersistableComment, String> {
}