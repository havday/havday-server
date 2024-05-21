package com.wane.reviewservice.application.port.`in`

interface CountReviewUseCase {
    fun countByProductId(productId: Long): Long
}