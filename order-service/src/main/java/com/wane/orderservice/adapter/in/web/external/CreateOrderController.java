package com.wane.orderservice.adapter.in.web.external;

import com.wane.orderservice.adapter.in.web.dto.request.CreateOrderRequest;
import com.wane.orderservice.adapter.in.web.dto.response.CreateOrderResponse;
import com.wane.orderservice.application.port.in.CreateOrderCommand;
import com.wane.orderservice.application.port.in.CreateOrderUseCase;
import com.wane.orderservice.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CreateOrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping("/api/v1/orders")
    public ResponseEntity<CreateOrderResponse> createOrder(
            @RequestHeader("memberId") Long memberId,
            @RequestBody CreateOrderRequest request
    ) {
        CreateOrderCommand command = CreateOrderCommand.of(memberId, request);
        Order order = createOrderUseCase.createOrder(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateOrderResponse(order.getId(), order.getCreatedAt().toLocalDate()));
    }
}
