package com.ggp.blog.infrastructure.persistence.repositories

import com.ggp.blog.domain.core.article.*
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ArticleRepository : ReactiveMongoRepository<Article, ArticleId> {
    fun findBySlug(slug: Slug): Mono<Article>
    fun findAllBy(pageable: Pageable): Flux<Article>
    fun deleteBySlug(slug: Slug)
    fun findAllBySlugIn(slugs: List<Slug>): Flux<Article>
    fun findAllByAuthor(author: Author, pageable: Pageable): Flux<Article>
    fun findAllByTagsContains(tags: Set<Tag>, pageable: Pageable): Flux<Article>
}