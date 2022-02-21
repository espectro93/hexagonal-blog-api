package com.ggp.blog.application.article

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ArticleRouter {
    @Bean
    fun articleRouter(articleHandler: ArticleHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            //TODO: check query params for author, feed and tags
            GET("/api/articles", articleHandler::getAllArticles)
            GET("/api/articles/{slug}", articleHandler::getArticleBySlug)
            POST("/api/articles", articleHandler::createArticle)
            PUT("/api/articles/{slug}", articleHandler::editArticle)
            DELETE("/api/articles/{slug}", articleHandler::deleteArticle)
            PATCH("/api/articles/{slug}/favor", articleHandler::favorArticle)
            PATCH("/api/articles/{slug}/disfavor", articleHandler::disfavorArticle)
        }
    }
}