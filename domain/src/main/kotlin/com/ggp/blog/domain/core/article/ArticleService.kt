package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.ports.out.LoadAllArticles
import com.ggp.blog.domain.ports.out.LoadArticle
import javax.annotation.Resource

@Resource
class ArticleService(
        private val loadArticle: LoadArticle,
        private val loadAll: LoadAllArticles
) {
}