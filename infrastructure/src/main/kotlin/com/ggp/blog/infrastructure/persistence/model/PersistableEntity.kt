package com.ggp.blog.infrastructure.persistence.model

import java.time.Instant

interface PersistableEntity {
    var id: String?
    var updatedAt: Instant?
}