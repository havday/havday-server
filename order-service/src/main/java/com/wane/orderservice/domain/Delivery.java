package com.wane.orderservice.domain;

import lombok.Getter;

public class Delivery {
	public static final int DELIVERY_FEE = 3000;
	public static final int NOT_NEED_DELIVERY_FEE_TOTAL_PRICE = 70000;

	public static boolean isDeliveryFeeNeeded(int totalPrice) {
		return totalPrice < NOT_NEED_DELIVERY_FEE_TOTAL_PRICE;
	}

}
