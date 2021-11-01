package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.user.FavoredArticleId
import kotlinx.coroutines.flow.Flow

interface LoadUserFavoredArticle {
    suspend fun loadAllBy(favoredArticleId: FavoredArticleId, page: Int, size: Int): Flow<Article>
}