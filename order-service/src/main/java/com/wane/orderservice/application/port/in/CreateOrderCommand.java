package com.wane.orderservice.application.port.in;

import com.wane.orderservice.adapter.in.web.dto.request.CreateOrderRequest;
import com.wane.orderservice.domain.PaymentType;
import com.wane.util.SelfValidating;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderCommand extends SelfValidating<CreateOrderCommand> {

	@NotNull(message = "memberId 는 필수 값 입니다.")
	private Long memberId;

	@NotNull(message = "addressId 는 필수 값 입니다.")
	private Long addressId;

	@Min(value = 0L, message = "총액은 양수여야 합니다.")
	private int totalPrice;

	boolean isDeliveryFeeExists;

	@Min(value = 0L, message = "포인트는 0 이상이어야 합니다.")
	private int usedPoint;

	private PaymentType paymentType;

	@NotEmpty(message = "상품들이 하나 이상 있어야 합니다.")
	private List<ProductItemCommand> productItemCommands;

	private CreateOrderCommand(Long memberId, Long addressId, int totalPrice, boolean isDeliveryFeeExists, int usedPoint, String paymentType, List<ProductItemCommand> productItemCommands) {
		this.memberId = memberId;
		this.addressId = addressId;
		this.totalPrice = totalPrice;
		this.isDeliveryFeeExists = isDeliveryFeeExists;
		this.usedPoint = usedPoint;
		this.productItemCommands = productItemCommands;
		this.paymentType = PaymentType.findByEnName(paymentType);

		this.validateSelf();
	}

	public static CreateOrderCommand of(CreateOrderRequest request) {

		List<ProductItemCommand> productItemCommands = request.productItems()
				.stream()
				.map(ProductItemCommand::new)
				.toList();

		return new CreateOrderCommand(request.memberId(), request.addressId(), request.totalPrice(), request.isDeliveryFeeExists(), request.usedPoint(), request.paymentType(), productItemCommands);
	}
}
