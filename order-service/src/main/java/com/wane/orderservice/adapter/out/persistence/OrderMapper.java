package com.wane.orderservice.adapter.out.persistence;

import com.wane.orderservice.adapter.out.persistence.jpa.OrderJpaEntity;
import com.wane.orderservice.adapter.out.persistence.jpa.PaymentJpaEntity;
import com.wane.orderservice.adapter.out.persistence.jpa.ProductItemJpaEntity;
import com.wane.orderservice.domain.Order;
import com.wane.orderservice.domain.Payment;
import com.wane.orderservice.domain.ProductItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

	public OrderJpaEntity toJpaEntity(Order order) {
		PaymentJpaEntity paymentJpaEntity = this.toJpaEntity(order.getPayment());
		List<ProductItemJpaEntity> productItemJpaEntities = order.getProductItems().stream().map(this::toJpaEntity)
				.toList();

		return OrderJpaEntity.builder()
				.memberId(order.getMemberId())
				.addressId(order.getAddressId())
				.totalPrice(order.getTotalPrice())
				.isDeliveryFeeExists(order.isDeliveryFeeExists())
				.usedPoint(order.getUsedPoint())
				.orderStatus(order.getOrderStatus())
				.payment(paymentJpaEntity)
				.productItems(productItemJpaEntities)
				.build();
	}

	public PaymentJpaEntity toJpaEntity(Payment payment) {
		return PaymentJpaEntity.builder()
				.price(payment.getPrice())
				.paymentType(payment.getPaymentType())
				.paymentStatus(payment.getPaymentStatus())
				.build();
	}

	public ProductItemJpaEntity toJpaEntity(ProductItem productItem) {
		return ProductItemJpaEntity.builder()
				.productId(productItem.getProductId())
				.quantity(productItem.getQuantity())
				.build();
	}

	public Order toDomainEntity(OrderJpaEntity orderJpaEntity) {
		Payment payment = this.toDomainEntity(orderJpaEntity.getPayment());
		List<ProductItem> productItems = orderJpaEntity.getProductItems().stream()
				.map(this::toDomainEntity)
				.toList();
		return Order.forMapper(
				orderJpaEntity.getId(),
				orderJpaEntity.getMemberId(),
				orderJpaEntity.getAddressId(),
				orderJpaEntity.getTotalPrice(),
				orderJpaEntity.isDeliveryFeeExists(),
				orderJpaEntity.getUsedPoint(),
				orderJpaEntity.getOrderStatus(),
				orderJpaEntity.getCreatedAt(),
				payment,
				productItems
		);
	}

	public Payment toDomainEntity(PaymentJpaEntity paymentJpaEntity) {
		return Payment.forMapper(
				paymentJpaEntity.getId(),
				paymentJpaEntity.getPrice(),
				paymentJpaEntity.getPaymentType(),
				paymentJpaEntity.getPaymentStatus(),
				paymentJpaEntity.getCreatedAt()
		);
	}

	public ProductItem toDomainEntity(ProductItemJpaEntity productItemJpaEntity) {
		return ProductItem.forMapper(
				productItemJpaEntity.getId(),
				productItemJpaEntity.getProductId(),
				productItemJpaEntity.getQuantity()
		);
	}
}
