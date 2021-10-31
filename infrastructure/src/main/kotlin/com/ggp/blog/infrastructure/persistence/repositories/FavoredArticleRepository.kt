package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.user.FavoredArticle
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface FavoredArticleRepository : ReactiveMongoRepository<FavoredArticle, String> {
}