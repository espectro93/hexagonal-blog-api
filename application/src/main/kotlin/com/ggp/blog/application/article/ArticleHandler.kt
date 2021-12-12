package com.ggp.blog.application.article

import com.ggp.blog.domain.core.article.*
import com.ggp.blog.domain.core.user.UserId
import com.ggp.blog.domain.ports.`in`.*
import com.mongodb.internal.connection.Server
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

//TODO: CHECK VALIDATION https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-fn-handler-functions

@Component
class ArticleHandler(
        val createArticleUseCase: CreateArticleUseCase,
        val editArticleUseCase: EditArticleUseCases,
        val getArticleUseCase: GetArticleUseCase,
        val deleteArticleUseCase: DeleteArticleUseCase,
        val favorArticleUseCase: FavorArticleUseCase,
        val disfavorArticleUseCase: DisfavorArticleUseCase
) {
    suspend fun createArticle(request: ServerRequest): ServerResponse =
            request.awaitBody<CreateArticleDto>().let { dto ->
                createArticleUseCase.create(
                        author = Author(dto.author),
                        slug = Slug(dto.slug),
                        title = Title(dto.title),
                        description = Description(dto.description),
                        body = Body(dto.body),
                        tags = dto.tags.map { Tag(it) }.toHashSet()
                ).let { created(URI.create("/api/articles/${it.id}")).buildAndAwait() }
            }

    suspend fun editArticle(request: ServerRequest): ServerResponse =
        request.awaitBody<EditArticleDto>().let { dto ->
                editArticleUseCase.editBy(
                        slug = Slug(request.pathVariable("slug")),
                        title = dto.title?.let { Title(it) },
                        description = dto.description?.let { Description(it) },
                        body = dto.body?.let { Body(it) },
                        tags = dto.tags?.map { Tag(it) }?.toHashSet()
                )?.let { ok().buildAndAwait() }
            }  ?: notFound().buildAndAwait()

    suspend fun getArticleBySlug(request: ServerRequest): ServerResponse =
            getArticleUseCase.getBySlug(Slug(request.pathVariable("slug")))?.let {
                ok().bodyValueAndAwait(ArticleDto.from(
                        it
                ))
            } ?: notFound().buildAndAwait()

    suspend fun getAllArticles(request: ServerRequest): ServerResponse =
            ok().bodyAndAwait(
                    getArticleUseCase.getAll(
                            request.attribute("page").orElseThrow() as Int,
                            request.attribute("size").orElseThrow() as Int
                    ).map {
                        ArticleDto.from(it)
                    }
            )

    suspend fun deleteArticle(request: ServerRequest): ServerResponse =
            runCatching {
                deleteArticleUseCase.deleteBySlug(Slug(request.pathVariable("slug")))
                ok().buildAndAwait()
            }
                    .getOrElse { notFound().buildAndAwait() }

    suspend fun favorArticle(request: ServerRequest): ServerResponse =
            runCatching {
                favorArticleUseCase.favorBySlug(UserId("GET FROM CONTEXT?"), Slug(request.pathVariable("slug")))
                ok().buildAndAwait()
            }
                    .getOrElse { notFound().buildAndAwait() }

    suspend fun disfavorArticle(request: ServerRequest): ServerResponse =
            runCatching {
                disfavorArticleUseCase.disfavorBySlug(UserId("GET FROM CONTEXT?"), Slug(request.pathVariable("slug")))
                ok().buildAndAwait()
            }
                    .getOrElse { notFound().buildAndAwait() }
}