package com.ggp.blog.infrastructure.persistence

import com.ggp.blog.domain.core.article.Article
import com.ggp.blog.domain.ports.out.DeleteArticle
import com.ggp.blog.domain.ports.out.LoadAllArticles
import com.ggp.blog.domain.ports.out.LoadArticle
import com.ggp.blog.domain.ports.out.StoreArticle

class ArticleStorageAdapter : LoadArticle, LoadAllArticles, StoreArticle, DeleteArticle {
    override suspend fun load(articleId: String): Article? {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllPage(): List<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun store(article: Article): Article {
        TODO("Not yet implemented")
    }

    override suspend fun delete(articleId: String) {
        TODO("Not yet implemented")
    }
}