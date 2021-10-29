package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.article.Article

interface LoadArticle {
    suspend fun load(articleId: String): Article?
}

interface LoadAllArticlesPaged {
    suspend fun loadAllPage(page: Int, size: Int): List<Article>
}

interface StoreArticle {
    suspend fun store(article: Article): Article
}

interface DeleteArticle {
    suspend fun delete(articleId: String)
}