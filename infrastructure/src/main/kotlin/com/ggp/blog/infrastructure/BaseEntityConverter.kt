package com.ggp.blog.infrastructure

import com.ggp.blog.domain.core.shared.BaseEntity
import java.util.*
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component

@Component
class BaseEntityConverter : AbstractMongoEventListener<BaseEntity>() {
    override fun onBeforeConvert(event: BeforeConvertEvent<BaseEntity>) {
        event.source.id ?: apply { event.source.id = UUID.randomUUID().toString() }
        super.onBeforeConvert(event)
    }
}