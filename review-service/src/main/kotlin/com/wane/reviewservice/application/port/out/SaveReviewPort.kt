package com.wane.reviewservice.application.port.out

import com.wane.reviewservice.domain.Review

interface SaveReviewPort {

    fun save(review: Review) : Review
}