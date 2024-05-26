package com.wane.orderservice.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {

	private String id;
	private Long memberId;
	private Long addressId;
	//TODO : total price 라는 말이 애매한가? 이걸 보고 배송비 포함인지, 포인트 빠지고 다 계산한 금액인가?
	private int totalPrice;
	private boolean isDeliveryFeeExists;
	private int usedPoint;
	private OrderStatus orderStatus;
	private LocalDateTime createdAt;

	private Payment payment;
	private List<ProductItem> productItems;

	private Order(String id, Long memberId, Long addressId, int totalPrice, boolean isDeliveryFeeExists, int usedPoint, OrderStatus orderStatus, LocalDateTime createdAt, Payment payment, List<ProductItem> productItems) {
		this.id = id;
		this.memberId = memberId;
		this.addressId = addressId;
		this.totalPrice = totalPrice;
		this.isDeliveryFeeExists = isDeliveryFeeExists;
		this.usedPoint = usedPoint;
		this.orderStatus = orderStatus;
		this.createdAt = createdAt;
		this.payment = payment;
		this.productItems = productItems;
	}

	private Order(Long memberId, Long addressId, int totalPrice, boolean isDeliveryFeeExists, int usedPoint, OrderStatus orderStatus, Payment payment, List<ProductItem> productItems) {
		this.memberId = memberId;
		this.addressId = addressId;
		this.totalPrice = totalPrice;
		this.isDeliveryFeeExists = isDeliveryFeeExists;
		this.usedPoint = usedPoint;
		this.orderStatus = orderStatus;
		this.payment = payment;
		this.productItems = productItems;
	}

	public static Order createOrder(Long memberId, Long addressId, int totalPrice, boolean isDeliveryFeeExists, int usedPoint, PaymentType paymentType, List<ProductItem> productItems) {

		switch (paymentType) {

			case NO_BANKBOOK -> {
				Payment payment = Payment.createNoBankBookPayment(totalPrice);
				return new Order(memberId, addressId, totalPrice, isDeliveryFeeExists, usedPoint, OrderStatus.BEFORE_DEPOSIT, payment, productItems);
			}
			case CARD -> {
				Payment payment = Payment.createCardPayment(totalPrice);
				return new Order(memberId, addressId, totalPrice, isDeliveryFeeExists, usedPoint, OrderStatus.PREPARED, payment, productItems);
			}
			case null -> throw new IllegalArgumentException("지원하지 않은 결제 타입입니다.");
		}
	}

	public static Order of(String id, Long memberId, Long addressId, int totalPrice, boolean isDeliveryFeeExists, int usedPoint, OrderStatus orderStatus, LocalDateTime orderDateTime, Payment payment, List<ProductItem> productItems) {
		return new Order(id, memberId, addressId, totalPrice, isDeliveryFeeExists, usedPoint, orderStatus, orderDateTime, payment, productItems);
	}
}
