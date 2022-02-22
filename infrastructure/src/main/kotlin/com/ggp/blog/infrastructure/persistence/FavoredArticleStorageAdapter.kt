package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.article.Slug
import com.ggp.blog.domain.core.user.FavoredArticle
import com.ggp.blog.domain.core.user.FavoredArticleId
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.ports.out.DeleteUserFavoredArticle
import com.ggp.blog.domain.ports.out.LoadUserFavoredArticle
import com.ggp.blog.domain.ports.out.StoreUserFavoredArticle
import com.ggp.blog.infrastructure.persistence.repositories.ArticleRepository
import com.ggp.blog.infrastructure.persistence.repositories.FavoredArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class FavoredArticleStorageAdapter(
    private val articleRepository: ArticleRepository,
    private val favoredArticleRepository: FavoredArticleRepository
) : LoadUserFavoredArticle, StoreUserFavoredArticle, DeleteUserFavoredArticle {
    override fun loadAllBy(favoredArticleId: FavoredArticleId, page: Int, size: Int): Flow<Article> {
        return favoredArticleRepository.findAllById(favoredArticleId, PageRequest.of(page, size))
            .collectList()
            .flatMapMany { favoredArticles ->
                articleRepository.findAllBySlugIn(favoredArticles.stream().map { it.slug }
                    .collect(Collectors.toList()))
            }
            .asFlow()
    }

    override suspend fun loadBy(userId: UserId, slug: Slug): FavoredArticle? {
        return favoredArticleRepository.findByUserIdAndSlug(userId, slug)
    }

    override suspend fun store(userFavoredArticle: FavoredArticle): FavoredArticle {
        return favoredArticleRepository.save(userFavoredArticle).awaitSingle()
    }

    override suspend fun deleteBy(favoredArticleId: FavoredArticleId) {
        favoredArticleRepository.deleteById(favoredArticleId)
    }
}