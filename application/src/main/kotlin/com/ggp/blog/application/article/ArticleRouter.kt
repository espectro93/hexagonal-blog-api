package com.ggp.blog.application.article

import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

class ArticleRouter {
    companion object {
        const val API_ARTICLES_SLUG = "/api/articles/{slug}"
    }

    @Bean
    fun articleRouter(articleHandler: ArticleHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            //TODO: check query params for author, feed and tags
            GET("/api/articles", articleHandler::getAllArticles)
            GET(API_ARTICLES_SLUG, articleHandler::getArticleBySlug)
            POST("/api/articles", articleHandler::createArticle)
            PUT(API_ARTICLES_SLUG, articleHandler::editArticle)
            DELETE(API_ARTICLES_SLUG, articleHandler::deleteArticle)
            PATCH("/api/articles/{slug}/favor", articleHandler::favorArticle)
            PATCH("/api/articles/{slug}/disfavor", articleHandler::disfavorArticle)
        }
    }
}