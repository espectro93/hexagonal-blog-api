package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.ports.`in`.*
import com.ggp.blog.domain.ports.out.DeleteArticle
import com.ggp.blog.domain.ports.out.LoadAllArticles
import com.ggp.blog.domain.ports.out.LoadArticle
import com.ggp.blog.domain.ports.out.StoreArticle
import kotlinx.coroutines.flow.Flow
import javax.annotation.Resource

@Resource
class ArticleService(
    private val loadArticle: LoadArticle,
    private val loadAll: LoadAllArticles,
    private val storeArticle: StoreArticle,
    private val deleteArticle: DeleteArticle
) : CreateArticleUseCase, EditArticleUseCases, GetArticleUseCase, DeleteArticleUseCase, FavorArticleUseCase,
    DisfavorArticleUseCase {
        override suspend fun create(article: Article): Article {
                TODO("Not yet implemented")
        }

        override suspend fun editBy(
                slug: Slug,
                title: Title?,
                description: Description?,
                body: Body?,
                tags: Set<Tag>?
        ): Article {
                TODO("Not yet implemented")
        }

        override suspend fun getBySlug(slug: Slug): Article? {
                TODO("Not yet implemented")
        }

        override suspend fun getByAuthor(author: Author): Article? {
                TODO("Not yet implemented")
        }

        override suspend fun getByTag(tag: Tag): Article? {
                TODO("Not yet implemented")
        }

        override suspend fun getFeed(): Flow<Article> {
                TODO("Not yet implemented")
        }

        override suspend fun getAll(): Flow<Article> {
                TODO("Not yet implemented")
        }

        override suspend fun deleteBySlug(slug: Slug) {
                TODO("Not yet implemented")
        }

        override suspend fun favorBySlug(slug: Slug) {
                TODO("Not yet implemented")
        }

        override suspend fun disfavorBySlug(slug: Slug) {
                TODO("Not yet implemented")
        }
}