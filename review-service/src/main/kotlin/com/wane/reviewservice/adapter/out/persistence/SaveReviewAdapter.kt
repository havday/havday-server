package com.wane.reviewservice.adapter.out.persistence

import com.wane.reviewservice.adapter.out.persistence.jpa.ReviewJpaEntityRepository
import com.wane.reviewservice.application.port.out.SaveReviewPort
import com.wane.reviewservice.domain.Review
import org.springframework.stereotype.Repository

@Repository
class SaveReviewAdapter(
    private val reviewJpaEntityRepository: ReviewJpaEntityRepository,
    private val reviewMapper: ReviewMapper
) :SaveReviewPort {
    override fun save(review: Review): Review {
        val savedJpaEntity = reviewJpaEntityRepository.save(
            reviewMapper.toJpaEntity(review)
        )

        return reviewMapper.toDomain(savedJpaEntity)
    }
}