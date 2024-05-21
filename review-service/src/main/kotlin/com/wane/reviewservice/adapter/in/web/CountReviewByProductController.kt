package com.wane.reviewservice.adapter.`in`.web

import com.wane.reviewservice.adapter.`in`.web.dto.response.CountReviewResponse
import com.wane.reviewservice.application.port.`in`.CountReviewUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CountReviewByProductController(
    private val countReviewUseCase: CountReviewUseCase
) {

    @GetMapping("/api/v1/reviews/product/{productId}/count")
    fun countReviewByProductId(
        @PathVariable productId: Long
    ): ResponseEntity<CountReviewResponse> {
        val counts: Long = countReviewUseCase.countByProductId(productId)

        return ResponseEntity.ok(CountReviewResponse(counts))
    }
}