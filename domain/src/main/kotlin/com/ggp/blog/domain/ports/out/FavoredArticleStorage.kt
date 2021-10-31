package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.core.user.FavoredArticleUserId
import com.ggp.blog.domain.core.user.UserId
import kotlinx.coroutines.flow.Flow

interface LoadUserFavoredArticle {
    suspend fun loadAllBy(favoredArticleUserId: FavoredArticleUserId, page: Int, size: Int): Flow<Article>
}