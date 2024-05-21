package com.wane.reviewservice.application.port.`in`

data class CreateReviewCommand(
    val memberId: Long,
    val memberName: String,
    val productId: Long,
    val content: String
)
