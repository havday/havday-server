package com.wane.orderservice.application.port.in;

import com.wane.orderservice.adapter.in.web.dto.request.ProductItemRequest;
import com.wane.util.SelfValidating;
import lombok.Getter;

@Getter
public class ProductItemCommand extends SelfValidating<ProductItemCommand> {
	private Long productId;
	private int quantity;

	public ProductItemCommand(Long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
		this.validateSelf();
	}

	public ProductItemCommand(ProductItemRequest request) {
		this.productId = request.productId();
		this.quantity = request.quantity();
		this.validateSelf();
	}


}
