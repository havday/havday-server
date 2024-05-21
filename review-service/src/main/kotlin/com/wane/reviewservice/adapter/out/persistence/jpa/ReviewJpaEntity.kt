package com.wane.reviewservice.adapter.out.persistence.jpa

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener::class)
data class ReviewJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(nullable = false, updatable = false)
    val memberId: Long,

    @Column(nullable = false, updatable = false)
    val memberName: String,

    @Column(nullable = false, updatable = false)
    val productId: Long,

    @Lob
    @Column(nullable = false, updatable = true)
    val content: String,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime?

){
    constructor(
        memberId: Long,
        memberName: String,
        productId: Long,
        content: String
    ) : this(
        null,
        memberId,
        memberName,
        productId,
        content,
        null
    )

}
