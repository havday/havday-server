package com.wane.reviewservice.application.port.`in`

interface CreateReviewUseCase {
    fun create(
        createReviewCommand: CreateReviewCommand
    )
}