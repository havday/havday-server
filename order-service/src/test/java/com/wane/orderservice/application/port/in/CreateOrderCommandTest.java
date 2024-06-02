package com.wane.orderservice.application.port.in;

import com.wane.orderservice.adapter.in.web.dto.request.CreateOrderRequest;
import com.wane.orderservice.adapter.in.web.dto.request.ProductItemRequest;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreateOrderCommandTest {

    @DisplayName("CreateOrderCommand 에서 memberId 는 필수 값 입니다.")
    @Test
    void createOrderCommandMemberIdNotEmpty() {
        //given
        ProductItemRequest productItemRequest = new ProductItemRequest(1L, 2);
        CreateOrderRequest request = new CreateOrderRequest(1L, 100, true, 1000, "card", List.of(productItemRequest));

        //when //then
        assertThatThrownBy(() -> CreateOrderCommand.of(null, request))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("CreateOrderCommand 에서 addressId 는 필수 값 입니다.")
    @Test
    void createOrderCommandAddressIdNotEmpty() {
        //given
        ProductItemRequest productItemRequest = new ProductItemRequest(1L, 2);
        CreateOrderRequest request = new CreateOrderRequest( null, 100, true, 1000, "card", List.of(productItemRequest));

        //when //then
        assertThatThrownBy(() -> CreateOrderCommand.of(1L, request))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("CreateOrderCommand 에서 totalPrice 는 0 이상이여야 합니다.")
    @Test
    void createOrderCommandTotalPriceGreaterThanZero() {
        //given
        ProductItemRequest productItemRequest = new ProductItemRequest(1L, 2);
        CreateOrderRequest request = new CreateOrderRequest( 1L, -1, true, 1000, "card", List.of(productItemRequest));

        //when //then
        assertThatThrownBy(() -> CreateOrderCommand.of(1L, request))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("CreateOrderCommand 에서 usedPoint 는 0 이상이여야 합니다.")
    @Test
    void createOrderCommandUsedPointGreaterThanZero() {
        //given
        ProductItemRequest productItemRequest = new ProductItemRequest(1L, 2);
        CreateOrderRequest request = new CreateOrderRequest( 1L, 100, true, -1, "card", List.of(productItemRequest));

        //when //then
        assertThatThrownBy(() -> CreateOrderCommand.of(1L, request))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @DisplayName("CreateOrderCommand 에서 paymentType이 일치해야 합니다.")
    @Test
    void createOrderCommandCorrectMatchesPaymentType() {
        //given
        ProductItemRequest productItemRequest = new ProductItemRequest(1L, 2);
        CreateOrderRequest request = new CreateOrderRequest( 1L, 100, true, -1, "wrong", List.of(productItemRequest));

        //when //then
        assertThatThrownBy(() -> CreateOrderCommand.of(1L, request))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("CreateOrderCommand 에서 productItem 은 비면 안됩니다.")
    @Test
    void createOrderCommandProductItemCommandNotEmpty() {
        //given
        CreateOrderRequest request = new CreateOrderRequest( 1L, 100, true, 100, "card", List.of());

        //when //then
        assertThatThrownBy(() -> CreateOrderCommand.of(1L, request))
                .isInstanceOf(ConstraintViolationException.class);
    }

}