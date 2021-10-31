package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.user.FavoredArticle
import com.ggp.blog.domain.core.user.FavoredArticleUserId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface FavoredArticleRepository : ReactiveMongoRepository<FavoredArticle, FavoredArticleUserId> {
    fun findAllById(favoredArticleUserId: FavoredArticleUserId, pageable: Pageable): Flux<FavoredArticle>
}