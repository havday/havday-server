package com.wane.reviewservice.adapter.`in`.web.dto.request

data class CreateReviewRequest(
    val memberName: String,
    val productId: Long,
    val content: String
)
