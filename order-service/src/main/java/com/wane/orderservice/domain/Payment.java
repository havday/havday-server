package com.wane.orderservice.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Payment {
	private Long id;
	private int price;
	private PaymentType paymentType;
	private PaymentStatus paymentStatus;
	private LocalDateTime createdAt;

	private Payment(Long id, int price, PaymentType paymentType, PaymentStatus paymentStatus, LocalDateTime createdAt) {
		this.id = id;
		this.price = price;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.createdAt = createdAt;
	}

	private Payment(int price, PaymentType paymentType, PaymentStatus paymentStatus) {
		this.price = price;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
	}

	public static Payment forMapper(Long id, int price, PaymentType paymentType, PaymentStatus paymentStatus, LocalDateTime createdAt) {
		return new Payment(id, price, paymentType, paymentStatus, createdAt);
	}

	public static Payment createNoBankBookPayment(int price) {
		return new Payment(price, PaymentType.NO_BANKBOOK, PaymentStatus.BEFORE_PAYED);
	}

	public static Payment createCardPayment(int price) {
		return new Payment(price, PaymentType.CARD, PaymentStatus.PAID);
	}
}
