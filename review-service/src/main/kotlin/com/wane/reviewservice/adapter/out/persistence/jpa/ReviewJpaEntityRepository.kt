package com.wane.reviewservice.adapter.out.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository

interface ReviewJpaEntityRepository : JpaRepository<ReviewJpaEntity, Long> {

    fun countReviewByProductId(productId: Long): Long
}