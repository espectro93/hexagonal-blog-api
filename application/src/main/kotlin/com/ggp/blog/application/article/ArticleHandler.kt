package com.ggp.blog.application.article

import com.ggp.blog.domain.core.article.ArticleService
import com.ggp.blog.domain.ports.`in`.*
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class ArticleHandler(
    val createArticleUseCase: CreateArticleUseCase,
    val editArticleUseCases: EditArticleUseCases,
    val getArticleUseCase: GetArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val favorArticleUseCase: FavorArticleUseCase,
    val disfavorArticleUseCase: DisfavorArticleUseCase,
    val articleService: ArticleService
) {
    //TODO: throw explicit bad request exception
    suspend fun getAllArticles(request: ServerRequest): ServerResponse =
        getArticleUseCase.getAll(
            request.attribute("page").orElseThrow() as Int,
            request.attribute("size").orElseThrow() as Int
        ).let {
            ok().bodyValueAndAwait("null")
        } ?: notFound().buildAndAwait()
}