package com.ggp.blog.application.article

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.time.Instant

internal class ArticleRouterTest {
    private val articleHandler: ArticleHandler = mockk()
    private val client = WebTestClient.bindToRouterFunction(
        ArticleRouter().articleRouter(articleHandler)
    ).build()

    @Test
    fun testGetArticles() {
        runBlocking {
            coEvery { articleHandler.getAllArticles(any()) }
                .returns(
                    ok().bodyValueAndAwait(
                        listOf(
                            ArticleDto(
                                id = "1",
                                author = "me",
                                title = "test",
                                description = "abc",
                                body = "123",
                                slug = "123",
                                tags = setOf(),
                                createdAt = "now",
                                updatedAt = "now"
                            )
                        )
                    )
                )

            client.get()
                .uri { builder -> builder.path("/api/articles").build() }
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk
                .expectBody()
                .json(
                    """
                [
                  {
                    "id": "1",
                    "author": "me",
                    "slug": "123",
                    "title": "test",
                    "description": "abc",
                    "body": "123",
                    "tags": [],
                    "updatedAt": "now",
                    "createdAt": "now"
                  }
                ]
                """.trimIndent())
        }
    }
}