package com.ggp.blog.application.article

import com.ggp.blog.domain.core.article.*
import java.time.ZoneId

data class CreateArticleDto(
        val author: String,
        val slug: String,
        val title: String,
        val description: String,
        val body: String,
        val tags: Set<String>,
)

data class EditArticleDto(
        val author: String,
        val title: String?,
        val description: String?,
        val body: String?,
        val tags: Set<String>?,
)

data class ArticleDto(
        val id: String,
        val author: String,
        val slug: String,
        val title: String,
        val description: String,
        val body: String,
        val tags: Set<String>,
        val updatedAt: String,
        val createdAt: String
) {
    companion object {
        fun from(model: Article): ArticleDto {
            return ArticleDto(
                    id = model.id!!.value,
                    author = model.author.value,
                    slug = model.slug.value,
                    title = model.title.value,
                    description = model.description.value,
                    body = model.body.value,
                    tags = model.tags.map { it.value }.toHashSet(),
                    updatedAt = model.updatedAt?.value?.atZone(ZoneId.of("Z")).toString(),
                    createdAt = model.createdAt?.value?.atZone(ZoneId.of("Z")).toString()
            )
        }
    }
}