package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.article.Slug
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.ports.out.*
import kotlinx.coroutines.flow.Flow

class ArticleStorageAdapter : LoadArticle, LoadAllArticles, StoreArticle, DeleteArticle, LoadUserFavoredArticle {
    override suspend fun loadBy(slug: Slug): Article? {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllPaged(): Flow<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun loadFeed(): Flow<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun store(article: Article): Article {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBy(slug: Slug) {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllPaged(userId: UserId): Flow<Article> {
        TODO("Not yet implemented")
    }

}