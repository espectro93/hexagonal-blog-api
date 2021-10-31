package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.article.ArticleId
import com.ggp.blog.domain.core.article.Slug
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ArticleRepository : ReactiveMongoRepository<Article, ArticleId> {
    fun findBySlug(slug: Slug): Mono<Article>
    fun findAllBy(pageable: Pageable): Flux<Article>
    fun deleteBySlug(slug: Slug)
    fun findAllBySlugIn(slugs: List<Slug>): Flux<Article>
}