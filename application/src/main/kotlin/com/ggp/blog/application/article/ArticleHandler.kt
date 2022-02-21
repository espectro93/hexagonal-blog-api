package com.ggp.blog.application.article

import com.ggp.blog.domain.core.article.*
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.ports.`in`.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI
import java.util.stream.Collectors

@Component
class ArticleHandler(
    val createArticleUseCase: CreateArticleUseCase,
    val editArticleUseCases: EditArticleUseCases,
    val getArticleUseCase: GetArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val favorArticleUseCase: FavorArticleUseCase,
    val disfavorArticleUseCase: DisfavorArticleUseCase
) {
    suspend fun getAllArticles(request: ServerRequest): ServerResponse =
        getArticleUseCase.getAll(
            request.attribute("page").orElseThrow() as Int,
            request.attribute("size").orElseThrow() as Int
        ).onEmpty { notFound().buildAndAwait() }
            .map { ArticleDto.fromDomain(it) }
            .let { ok().bodyAndAwait(it) }

    suspend fun getArticleBySlug(request: ServerRequest): ServerResponse =
        getArticleUseCase.getBySlug(Slug(request.pathVariable("slug")))?.let {
            ok().bodyValueAndAwait(it)
        } ?: notFound().buildAndAwait()

    suspend fun createArticle(request: ServerRequest): ServerResponse =
        createArticleUseCase.create(
            CreateArticleDto.toDomain(request.awaitBody())
        ).let { created(URI.create("/articles/${it.slug}")).buildAndAwait() }

    suspend fun editArticle(request: ServerRequest): ServerResponse =
        (request.awaitBody() as EditArticleDto).let { dto ->
            editArticleUseCases.editBy(
                author = Author(dto.author),
                slug = Slug(request.pathVariable("slug")),
                title = Title(dto.title),
                description = Description(dto.description),
                body = Body(dto.body),
                tags = dto.tags.stream().map { Tag(it) }.collect(Collectors.toSet())
            )?.let { ok().bodyValueAndAwait(it) } ?: notFound().buildAndAwait()
        }

    suspend fun deleteArticle(request: ServerRequest): ServerResponse =
        runCatching {
            deleteArticleUseCase.deleteBySlug(Slug(request.pathVariable("slug")))
            ok().buildAndAwait()
        }.getOrElse { notFound().buildAndAwait() }

    suspend fun favorArticle(request: ServerRequest): ServerResponse =
        runCatching {
            favorArticleUseCase.favorBySlug(UserId("123"), Slug(request.pathVariable("slug")))
            ok().buildAndAwait()
        }.getOrElse { notFound().buildAndAwait() }

    suspend fun disfavorArticle(request: ServerRequest): ServerResponse =
        runCatching {
            disfavorArticleUseCase.disfavorBySlug(UserId("123"), Slug(request.pathVariable("slug")))
            ok().buildAndAwait()
        }.getOrElse { notFound().buildAndAwait() }
}