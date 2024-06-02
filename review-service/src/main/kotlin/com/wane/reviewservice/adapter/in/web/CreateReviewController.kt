package com.wane.reviewservice.adapter.`in`.web

import com.wane.reviewservice.adapter.`in`.web.dto.request.CreateReviewRequest
import com.wane.reviewservice.application.port.`in`.CreateReviewCommand
import com.wane.reviewservice.application.port.`in`.CreateReviewUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateReviewController(
    private val createReviewUseCase: CreateReviewUseCase
) {
    @PostMapping("/api/v1/reviews")
    fun createReview(
        @RequestHeader("memberId") memberId: Long,
        @RequestBody request: CreateReviewRequest
    ) : ResponseEntity<Void> {

        val command = CreateReviewCommand(
            memberId,
            request.memberName,
            request.productId,
            request.content
        )
        createReviewUseCase.create(command)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}