package com.wane.reviewservice.application.service

import com.wane.reviewservice.application.port.`in`.CreateReviewCommand
import com.wane.reviewservice.application.port.`in`.CreateReviewUseCase
import com.wane.reviewservice.application.port.out.SaveReviewPort
import com.wane.reviewservice.domain.Review
import org.springframework.stereotype.Service

@Service
class CreateReviewService(
    private val saveReviewPort: SaveReviewPort
) : CreateReviewUseCase {

    override fun create(createReviewCommand: CreateReviewCommand) {
        val review = Review(
            createReviewCommand.memberId,
            createReviewCommand.memberName,
            createReviewCommand.productId,
            createReviewCommand.content
        )

        val savedReview = saveReviewPort.save(review)

    }
}