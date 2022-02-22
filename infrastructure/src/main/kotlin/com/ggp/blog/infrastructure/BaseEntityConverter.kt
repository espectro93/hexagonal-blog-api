package com.ggp.blog.infrastructure

import com.ggp.blog.domain.core.shared.BaseEntity
import com.ggp.blog.domain.core.shared.CreatedDate
import com.ggp.blog.domain.core.shared.Identifiable
import com.ggp.blog.domain.core.shared.UpdatedDate
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
@Suppress("UNCHECKED_CAST")
class BaseEntityConverter<T : Identifiable> : AbstractMongoEventListener<BaseEntity<T>>() {
    override fun onBeforeConvert(event: BeforeConvertEvent<BaseEntity<T>>) {
        val idConstructor = event.source::javaClass.get().getField("id").declaringClass.getConstructor()
        event.source.id ?: apply { event.source.id = idConstructor.newInstance(UUID.randomUUID().toString()) as T }
        event.source.createdAt ?: apply { event.source.createdAt = CreatedDate(Instant.now()) }
        event.source.updatedAt = UpdatedDate(Instant.now())
        super.onBeforeConvert(event)
    }
}