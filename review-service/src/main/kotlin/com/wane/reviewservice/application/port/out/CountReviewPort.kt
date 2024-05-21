package com.wane.reviewservice.application.port.out

interface CountReviewPort {
    fun countByProductId(productId: Long): Long
}