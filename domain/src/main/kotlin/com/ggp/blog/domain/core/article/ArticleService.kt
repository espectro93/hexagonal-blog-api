package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.ports.out.DeleteArticle
import com.ggp.blog.domain.ports.out.LoadAllArticles
import com.ggp.blog.domain.ports.out.LoadArticle
import com.ggp.blog.domain.ports.out.StoreArticle
import javax.annotation.Resource

@Resource
class ArticleService(
        private val loadArticle: LoadArticle,
        private val loadAll: LoadAllArticles,
        private val storeArticle: StoreArticle,
        private val deleteArticle: DeleteArticle
) {
}