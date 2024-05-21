package com.wane.reviewservice.application.service

import com.wane.reviewservice.application.port.`in`.CountReviewUseCase
import com.wane.reviewservice.application.port.out.CountReviewPort
import org.springframework.stereotype.Service

@Service
class CountReviewService(
    private val countReviewPort: CountReviewPort
): CountReviewUseCase {
    override fun countByProductId(productId: Long): Long {
       val reviewCounts: Long = countReviewPort.countByProductId(productId)

        return reviewCounts
    }
}