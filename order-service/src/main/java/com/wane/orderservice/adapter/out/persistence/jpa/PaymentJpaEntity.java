package com.wane.orderservice.adapter.out.persistence.jpa;

import com.wane.orderservice.domain.PaymentStatus;
import com.wane.orderservice.domain.PaymentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "payment")
public class PaymentJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int price;

	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@CreatedDate
	private LocalDateTime createdAt;

	@Builder
	public PaymentJpaEntity(Long id, int price, PaymentType paymentType, PaymentStatus paymentStatus, LocalDateTime createdAt) {
		this.id = id;
		this.price = price;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.createdAt = createdAt;
	}
}
