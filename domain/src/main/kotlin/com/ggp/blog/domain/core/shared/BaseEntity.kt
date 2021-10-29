package com.ggp.blog.domain.core.shared

import java.time.Instant

interface BaseEntity {
    var id: String?
    var createdAt: Instant
    var updatedAt: Instant
}