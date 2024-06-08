package com.wane.reviewservice.adapter.out.persistence

import com.wane.reviewservice.adapter.out.persistence.jpa.ReviewJpaEntity
import com.wane.reviewservice.adapter.out.persistence.jpa.ReviewJpaEntityRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CountReviewAdapterTest {

    @Autowired
    private lateinit var reviewJpaEntityRepository: ReviewJpaEntityRepository

    @Autowired
    private lateinit var countReviewAdapter: CountReviewAdapter

    @Test
    fun `returns correct count when reviews exist`() {
        // given
        val productId = 1L
        val reviewJpaEntity1 = ReviewJpaEntity(1L, "박재완", productId, "모자 짱짱이에용")
        val reviewJpaEntity2 = ReviewJpaEntity(2L, "박재완", productId, "모자 짱짱이에용")
        reviewJpaEntityRepository.saveAll(listOf(reviewJpaEntity1, reviewJpaEntity2))

        // when
        val result = countReviewAdapter.countByProductId(productId)

        // then
        assertThat(result).isEqualTo(2L)
    }

    @Test
    fun `returns zero when no reviews exist`() {
        // given
        // when
        val result = countReviewAdapter.countByProductId(2L)

        // then
        assertThat(result).isEqualTo(0L)
    }
}