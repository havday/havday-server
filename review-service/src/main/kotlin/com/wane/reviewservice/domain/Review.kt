package com.wane.reviewservice.domain

import java.time.LocalDateTime

data class Review(
    val id: Long?,
    val memberId: Long,
    val memberName: String,
    val productId: Long,
    val content: String,
    val createdAt: LocalDateTime?,
) {
    constructor(
        memberId: Long,
        memberName: String,
        productId: Long,
        content: String,
    ) : this(
        null,
        memberId,
        memberName,
        productId,
        content,
        null
    )

    fun create(
        memberId: Long,
        memberName: String,
        productId: Long,
        content: String
    ): Review {
        return Review(memberId, memberName, productId, content);
    }
}

