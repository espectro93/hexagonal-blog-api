package com.ggp.blog.domain.ports.out

import com.ggp.blog.domain.core.article.ParentPostCommentId
import com.ggp.blog.domain.core.article.PostComment
import com.ggp.blog.domain.core.article.PostCommentId
import com.ggp.blog.domain.core.article.Slug
import kotlinx.coroutines.flow.Flow

interface LoadPostComment {
    suspend fun loadBy(id: PostCommentId): PostComment?
}

interface LoadAllPostCommentsForArticle {
    suspend fun loadAllBy(slug: Slug, page: Int, size: Int): Flow<PostComment>
}

interface LoadAllPostCommentsForParentPostComment {
    suspend fun loadAllBy(parentPostCommentId: ParentPostCommentId, page: Int, size: Int): Flow<PostComment>
}

interface StorePostComment {
    suspend fun store(postComment: PostComment): PostComment
}

interface DeletePostComment {
    suspend fun deleteBy(postCommentId: PostCommentId)
}