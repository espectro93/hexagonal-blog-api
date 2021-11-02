package com.ggp.blog.domain.ports.`in`

import com.ggp.blog.domain.core.article.*
import com.ggp.blog.domain.core.user.UserId
import kotlinx.coroutines.flow.Flow

interface CreateArticleUseCase {
    suspend fun create(article: Article): Article
}

interface EditArticleUseCases {
    suspend fun editBy(slug: Slug, title: Title?, description: Description?, body: Body?, tags: Set<Tag>?): Article?
}

interface GetArticleUseCase {
    suspend fun getBySlug(slug: Slug): Article?
    suspend fun getByAuthor(author: Author, page: Int, size: Int): Flow<Article>
    suspend fun getByTag(tags: Set<Tag>, page: Int, size: Int): Flow<Article>
    suspend fun getFeed(page: Int, size: Int): Flow<Article>
    suspend fun getAll(page: Int, size: Int): Flow<Article>
}

interface DeleteArticleUseCase {
    suspend fun deleteBySlug(slug: Slug)
}

interface FavorArticleUseCase {
    suspend fun favorBySlug(userId: UserId, slug: Slug)
}

interface DisfavorArticleUseCase {
    suspend fun disfavorBySlug(userId: UserId, slug: Slug)
}