package com.wane.reviewservice.adapter.out.persistence

import com.wane.reviewservice.adapter.out.persistence.jpa.ReviewJpaEntity
import com.wane.reviewservice.domain.Review
import org.springframework.stereotype.Component

@Component
class ReviewMapper {

    fun toDomain(
        reviewJpaEntity: ReviewJpaEntity
    ): Review {
        return Review(
            reviewJpaEntity.id,
            reviewJpaEntity.memberId,
            reviewJpaEntity.memberName,
            reviewJpaEntity.productId,
            reviewJpaEntity.content,
            reviewJpaEntity.createdAt
        )
    }

    fun toJpaEntity(
        review: Review
    ) : ReviewJpaEntity {
        return ReviewJpaEntity(
            review.memberId,
            review.memberName,
            review.productId,
            review.content
        )
    }


}