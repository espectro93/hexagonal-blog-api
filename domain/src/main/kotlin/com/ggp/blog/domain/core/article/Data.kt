package com.ggp.blog.domain.core.article

import com.ggp.blog.domain.core.shared.BaseEntity
import com.ggp.blog.domain.core.shared.CreatedDate
import com.ggp.blog.domain.core.shared.Identifiable
import com.ggp.blog.domain.core.shared.UpdatedDate
import java.time.Instant

data class ArticleId(override val value: String) : Identifiable
data class Slug(val value: String)
data class Title(val value: String)
data class Description(val value: String)
data class Body(val value: String)
data class Tag(val value: String)
data class Author(val value: String)


data class Article(
        override var id: ArticleId? = null,
        val author: Author,
        val slug: Slug,
        val title: Title,
        val description: Description,
        val body: Body,
        val tags: Set<Tag>,
        override var updatedAt: UpdatedDate? = null,
        override var createdAt: CreatedDate? = null
) : BaseEntity<ArticleId> {

    fun writeComment(author: Author, body: Body): PostComment {
        return PostComment(
                slug = this.slug,
                author = author,
                body = body,
                createdAt = CreatedDate(Instant.now()),
                updatedAt = UpdatedDate(Instant.now())
        )
    }
}

data class PostCommentId(override val value: String) : Identifiable
data class ParentPostCommentId(val value: String)

data class PostComment(
        override var id: PostCommentId? = null,
        val slug: Slug,
        val parentPostCommentId: ParentPostCommentId? = null,
        val author: Author,
        val body: Body,
        override var updatedAt: UpdatedDate? = null,
        override var createdAt: CreatedDate? = null
) : BaseEntity<PostCommentId>