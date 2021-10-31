package com.ggp.blog.domain.ports.`in`

import com.ggp.blog.domain.core.article.*
import kotlinx.coroutines.flow.Flow

interface CreateArticleUseCase {
    suspend fun create(article: Article): Article
}

interface EditArticleUseCases {
    suspend fun editBy(slug: Slug, title: Title?, description: Description?, body: Body?, tags: Set<Tag>?): Article
}

interface GetArticleUseCase {
    suspend fun getBySlug(slug: Slug): Article?
    suspend fun getByAuthor(author: Author): Article?
    suspend fun getByTag(tag: Tag): Article?
    suspend fun getFeed(): Flow<Article>
    suspend fun getAll(): Flow<Article>
}

interface DeleteArticleUseCase {
    suspend fun deleteBySlug(slug: Slug)
}

interface FavorArticleUseCase {
    suspend fun favorBySlug(slug: Slug)
}

interface DisfavorArticleUseCase {
    suspend fun disfavorBySlug(slug: Slug)
}