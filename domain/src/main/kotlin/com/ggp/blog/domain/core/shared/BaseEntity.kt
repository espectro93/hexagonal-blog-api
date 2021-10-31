package com.ggp.blog.domain.core.shared

import java.time.Instant

data class CreatedDate(val value: Instant)
data class UpdatedDate(val value: Instant)

interface Identifiable {
    val value: String
}

interface BaseEntity<T : Identifiable> {
    var id: T?
    var createdAt: CreatedDate?
    var updatedAt: UpdatedDate?
}