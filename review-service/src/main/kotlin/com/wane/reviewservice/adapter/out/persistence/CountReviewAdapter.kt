package com.wane.reviewservice.adapter.out.persistence

import com.wane.reviewservice.adapter.out.persistence.jpa.ReviewJpaEntityRepository
import com.wane.reviewservice.application.port.out.CountReviewPort
import org.springframework.stereotype.Repository

@Repository
class CountReviewAdapter(
    private val reviewJpaEntityRepository: ReviewJpaEntityRepository
) : CountReviewPort {
    override fun countByProductId(productId: Long): Long {
        return reviewJpaEntityRepository.countReviewByProductId(productId)
    }
}