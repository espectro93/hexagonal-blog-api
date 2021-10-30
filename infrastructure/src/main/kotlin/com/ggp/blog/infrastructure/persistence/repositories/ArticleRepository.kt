package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.infrastructure.persistence.model.PersistableArticle
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ArticleRepository : ReactiveMongoRepository<PersistableArticle, String> {
}