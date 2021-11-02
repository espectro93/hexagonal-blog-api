package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.ports.`in`.*
import com.ggp.blog.domain.ports.out.*
import kotlinx.coroutines.flow.Flow
import javax.annotation.Resource

@Resource
class ArticleService(
    private val loadArticle: LoadArticle,
    private val loadUser: LoadUser,
    private val loadUserFavoredArticle: LoadUserFavoredArticle,
    private val loadAll: LoadAllArticles,
    private val storeArticle: StoreArticle,
    private val storeFavoredArticle: StoreUserFavoredArticle,
    private val deleteArticle: DeleteArticle,
    private val deleteFavoredArticle: DeleteUserFavoredArticle
) : CreateArticleUseCase, EditArticleUseCases, GetArticleUseCase, DeleteArticleUseCase, FavorArticleUseCase,
    DisfavorArticleUseCase {
    override suspend fun create(article: Article): Article {
        return storeArticle.store(article)
    }

    override suspend fun editBy(
        slug: Slug,
        title: Title?,
        description: Description?,
        body: Body?,
        tags: Set<Tag>?
    ): Article? {
        return loadArticle.loadBy(slug)?.let {
            Article(
                id = it.id!!,
                author = it.author,
                slug = slug,
                title = title ?: it.title,
                description = description ?: it.description,
                body = body ?: it.body,
                tags = tags ?: it.tags,
            )
        }
    }

    override suspend fun getBySlug(slug: Slug): Article? {
        return loadArticle.loadBy(slug)
    }

    override suspend fun getByAuthor(author: Author, page: Int, size: Int): Flow<Article> {
        return loadAll.loadAllBy(author, page, size)
    }

    override suspend fun getByTag(tags: Set<Tag>, page: Int, size: Int): Flow<Article> {
        return loadAll.loadAllBy(tags, page, size)
    }

    override suspend fun getFeed(page: Int, size: Int): Flow<Article> {
        return loadAll.loadAllBy(page, size)
    }

    override suspend fun getAll(page: Int, size: Int): Flow<Article> {
        return loadAll.loadAllBy(page, size)
    }

    override suspend fun deleteBySlug(slug: Slug) {
        deleteArticle.deleteBy(slug)
    }

    override suspend fun favorBySlug(userId: UserId, slug: Slug) {
        val favoredArticle = loadArticle.loadBy(slug)?.let {
            loadUser.loadBy(userId)?.favorArticle(slug)?.let {
                storeFavoredArticle.store(it)
            }
        }
        favoredArticle ?: throw Exception("Error! Could not favor article with slug $slug")
    }

    override suspend fun disfavorBySlug(userId: UserId, slug: Slug) {
        loadUserFavoredArticle.loadBy(userId, slug)?.let {
            deleteFavoredArticle.deleteBy(it.id!!)
        } ?: throw Exception("Error! Disfavoring article with slug $slug failed")
    }
}