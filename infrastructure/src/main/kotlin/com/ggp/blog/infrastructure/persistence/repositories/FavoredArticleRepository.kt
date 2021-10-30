package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.infrastructure.persistence.model.PersistableFavoredArticle
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface FavoredArticleRepository : ReactiveMongoRepository<PersistableFavoredArticle, String> {
}