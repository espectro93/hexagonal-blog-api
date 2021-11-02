package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.article.Slug
import com.ggp.blog.domain.core.user.FavoredArticle
import com.ggp.blog.domain.core.user.FavoredArticleId
import com.ggp.blog.domain.core.user.UserId
import kotlinx.coroutines.flow.Flow

interface LoadUserFavoredArticle {
    suspend fun loadAllBy(favoredArticleId: FavoredArticleId, page: Int, size: Int): Flow<Article>
    suspend fun loadBy(userId: UserId, slug: Slug): FavoredArticle?
}

interface StoreUserFavoredArticle {
    suspend fun store(userFavoredArticle: FavoredArticle): FavoredArticle
}

interface DeleteUserFavoredArticle{
    suspend fun deleteBy(favoredArticleId: FavoredArticleId)
}