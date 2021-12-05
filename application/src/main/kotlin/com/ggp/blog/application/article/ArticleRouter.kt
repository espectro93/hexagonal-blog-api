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
            GET("/api/articles", articleHandler::getAllArticles)
        }
    }
}