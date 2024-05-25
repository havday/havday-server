package com.wane.orderservice.application.service;

import com.wane.exception.CustomException;
import com.wane.orderservice.application.port.in.CreateOrderCommand;
import com.wane.orderservice.application.port.in.CreateOrderUseCase;
import com.wane.orderservice.application.port.out.*;
import com.wane.orderservice.domain.Delivery;
import com.wane.orderservice.domain.Order;
import com.wane.orderservice.domain.ProductItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wane.exception.ErrorCode.*;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateOrderService implements CreateOrderUseCase {

	private final CreateOrderPort createOrderPort;
	private final FindProductIdAndPriceListPort findProductIdAndPriceListPort;
	private final FindProductIdAndQuantityListPort findProductQuantityPort;
	private final CreateDeliveryPort createDeliveryPort;
	private final DecreaseProductQuantityPort decreaseProductQuantityPort;

	@Override
	public void createOrder(CreateOrderCommand command) {

		isDeliveryFeeCorrectWithTotalPrice(command);

		List<ProductItem> productItems = command.getProductItemCommands().stream()
				.map(productItemCommand -> ProductItem.create(productItemCommand.getProductId(), productItemCommand.getQuantity()))
				.toList();

		List<ProductIdAndPrice> productIdAndPriceList = findProductIdAndPriceListPort.findProductIdAndPriceListByProductIds(productItems.stream().map(ProductItem::getProductId).toList());
		isTotalPriceAndProductPriceSameOrElseThrow(command, productItems, productIdAndPriceList);

		List<ProductIdAndQuantity> productIdAndQuantityList = findProductQuantityPort.findProductIdAndQuantityListByProductIds(productItems.stream().map(ProductItem::getProductId).toList());
		isProductQuantityEnoughOrElseThrow(productItems, productIdAndQuantityList);

		decreaseProductQuantityPort.decreaseProductQuantity(productItems);

		Order order = Order.createOrder(
				command.getMemberId(),
				command.getAddressId(),
				command.getTotalPrice(),
				command.isDeliveryFeeExists(),
				command.getUsedPoint(),
				command.getPaymentType(),
				productItems
		);
		Order savedOrder = createOrderPort.createOrder(order);

		createDeliveryPort.createDelivery(new CreateDeliveryCommand(savedOrder.getMemberId(), savedOrder.getAddressId(), savedOrder.getId()));


	}

	private void isProductQuantityEnoughOrElseThrow(List<ProductItem> productItems, List<ProductIdAndQuantity> productIdAndQuantityList) {
		Map<Long, Integer> productIdToQuantityMap = productIdAndQuantityList.stream()
				.collect(Collectors.toMap(ProductIdAndQuantity::id, ProductIdAndQuantity::quantity));

		for (ProductItem productItem : productItems) {
			int quantity = productIdToQuantityMap.get(productItem.getId());
			if (quantity < productItem.getQuantity()) {
				throw new CustomException(PRODUCT_QUANTITY_NOT_ENOUGH);
			}
		}
	}

	private void isDeliveryFeeCorrectWithTotalPrice(CreateOrderCommand command) {
		boolean deliveryFeeNeeded = Delivery.isDeliveryFeeNeeded(command.getTotalPrice() + command.getUsedPoint());
		if (deliveryFeeNeeded != command.isDeliveryFeeExists()) {
			throw new CustomException(DELIVERY_FEE_NOT_MATCH);
		}
	}


	private void isTotalPriceAndProductPriceSameOrElseThrow(CreateOrderCommand command, List<ProductItem> productItems, List<ProductIdAndPrice> productIdAndPrices) {
		int calculateTotalPriceByProducts = command.getUsedPoint() + (command.isDeliveryFeeExists() ? Delivery.DELIVERY_FEE : 0);

		Map<Long, Integer> productIdToPriceMap = productIdAndPrices.stream()
				.collect(Collectors.toMap(ProductIdAndPrice::id, ProductIdAndPrice::price));

		for (ProductItem productItem : productItems) {
			int price = productIdToPriceMap.get(productItem.getId());
			calculateTotalPriceByProducts += price * productItem.getQuantity();
		}

		if (command.getTotalPrice() != calculateTotalPriceByProducts) {
			throw new CustomException(PRODUCT_PRICE_NOT_MATCH);
		}
	}
}
