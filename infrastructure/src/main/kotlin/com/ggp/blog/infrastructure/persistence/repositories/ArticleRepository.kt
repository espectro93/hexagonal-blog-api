package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.article.Article
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ArticleRepository : ReactiveMongoRepository<Article, String> {
}