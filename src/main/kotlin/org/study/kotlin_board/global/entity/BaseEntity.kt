package org.study.kotlin_board.global.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
    val createdAt: LocalDateTime = LocalDateTime.now()
    var updatedAt: LocalDateTime? = null
        protected set

    fun updatedAt() {
        this.updatedAt = LocalDateTime.now()
    }
}
