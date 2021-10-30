package com.ggp.blog.infrastructure

import com.ggp.blog.infrastructure.persistence.model.PersistableEntity
import java.util.*
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class BaseEntityConverter : AbstractMongoEventListener<PersistableEntity>() {
    override fun onBeforeConvert(event: BeforeConvertEvent<PersistableEntity>) {
        event.source.id ?: apply { event.source.id = UUID.randomUUID().toString() }
        event.source.createdAt ?: apply { event.source.createdAt = Instant.now() }
        event.source.updatedAt = Instant.now()
        super.onBeforeConvert(event)
    }
}