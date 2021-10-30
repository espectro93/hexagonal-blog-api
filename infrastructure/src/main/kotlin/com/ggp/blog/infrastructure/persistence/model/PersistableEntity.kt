package com.ggp.blog.infrastructure.persistence.model

import java.time.Instant

interface PersistableEntity {
    var id: String?
    var createdAt: Instant?
    var updatedAt: Instant?
}