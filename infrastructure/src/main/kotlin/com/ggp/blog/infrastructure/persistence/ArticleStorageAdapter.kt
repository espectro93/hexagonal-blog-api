package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.article.Slug
import com.ggp.blog.domain.ports.out.DeleteArticle
import com.ggp.blog.domain.ports.out.LoadAllArticles
import com.ggp.blog.domain.ports.out.LoadArticle
import com.ggp.blog.domain.ports.out.StoreArticle
import com.ggp.blog.infrastructure.persistence.repositories.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.springframework.data.domain.PageRequest

class ArticleStorageAdapter(private val articleRepository: ArticleRepository) : LoadArticle, LoadAllArticles,
    StoreArticle, DeleteArticle {
    override suspend fun loadBy(slug: Slug): Article? {
        return articleRepository.findBySlug(slug).awaitSingleOrNull()
    }

    override suspend fun loadAllBy(page: Int, size: Int): Flow<Article> {
        return articleRepository.findAllBy(PageRequest.of(page, size)).asFlow()
    }

    override suspend fun loadAllBy(): Flow<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun store(article: Article): Article {
        return articleRepository.save(article).awaitSingle()
    }

    override suspend fun deleteBy(slug: Slug) {
        articleRepository.deleteBySlug(slug)
    }
}