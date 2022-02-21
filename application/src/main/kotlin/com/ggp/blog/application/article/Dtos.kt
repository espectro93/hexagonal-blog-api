package com.ggp.blog.application.article

import com.ggp.blog.domain.core.article.*
import com.ggp.blog.domain.core.shared.CreatedDate
import com.ggp.blog.domain.core.shared.UpdatedDate
import java.time.ZonedDateTime
import java.util.stream.Collectors

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
        fun fromDomain(model: Article): ArticleDto {
            return ArticleDto(
                id = model.id!!.value,
                author = model.author.value,
                slug = model.slug.value,
                title = model.slug.value,
                description = model.description.value,
                body = model.body.value,
                tags = model.tags.stream().map { it.value }.collect(Collectors.toSet()),
                updatedAt = model.updatedAt.toString(),
                createdAt = model.createdAt.toString()
            )
        }
    }
}

data class CreateArticleDto(
    val author: String,
    val slug: String,
    val title: String,
    val description: String,
    val body: String,
    val tags: Set<String>
) {
    companion object {
        fun toDomain(dto: CreateArticleDto): Article {
            return Article(
                author = Author(dto.author),
                slug = Slug(dto.slug),
                title = Title(dto.title),
                description = Description(dto.description),
                body = Body(dto.body),
                tags = dto.tags.stream().map { Tag(it) }.collect(Collectors.toSet())
            )
        }
    }
}

data class EditArticleDto(
    val author: String,
    val title: String,
    val description: String,
    val body: String,
    val tags: Set<String>
)