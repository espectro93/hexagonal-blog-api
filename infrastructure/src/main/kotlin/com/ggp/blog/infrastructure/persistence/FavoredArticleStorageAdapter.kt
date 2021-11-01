package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.user.FavoredArticleId
import com.ggp.blog.domain.ports.out.LoadUserFavoredArticle
import com.ggp.blog.infrastructure.persistence.repositories.ArticleRepository
import com.ggp.blog.infrastructure.persistence.repositories.FavoredArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.domain.PageRequest
import java.util.stream.Collectors

class FavoredArticleStorageAdapter(
    private val articleRepository: ArticleRepository,
    private val favoredArticleRepository: FavoredArticleRepository
) : LoadUserFavoredArticle {
    override suspend fun loadAllBy(favoredArticleId: FavoredArticleId, page: Int, size: Int): Flow<Article> {
        return favoredArticleRepository.findAllById(favoredArticleId, PageRequest.of(page, size))
            .collectList()
            .flatMapMany { slugs ->
                articleRepository.findAllBySlugIn(slugs.stream().map { it.slug }
                    .collect(Collectors.toList()))
            }
            .asFlow()
    }
}