package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.article.Article

//TODO: Load Articles By Filters : Tags, Author, Favorites + Pageable
//TODO: Load Articles Feed -> Load From Favorites newest articles

interface LoadArticle {
    suspend fun load(articleId: String): Article?
}

interface LoadAllArticles {
    suspend fun loadAllPage(): List<Article>
}

interface StoreArticle {
    suspend fun store(article: Article): Article
}

interface DeleteArticle {
    suspend fun delete(articleId: String)
}