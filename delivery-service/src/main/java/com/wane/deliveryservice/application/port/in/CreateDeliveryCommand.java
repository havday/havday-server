package com.wane.deliveryservice.application.port.in;

import com.wane.util.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateDeliveryCommand extends SelfValidating<CreateDeliveryCommand> {
    @NotNull(message = "Member Id 는 필수 값 입니다.")
    private final Long memberId;
    @NotNull(message = "Address Id 는 필수 값 입니다.")
    private final Long addressId;
    @NotNull(message = "Order Id 는 필수 값 입니다.")
    private final String orderId;

    public CreateDeliveryCommand(Long memberId, Long addressId, String orderId) {
        this.memberId = memberId;
        this.addressId = addressId;
        this.orderId = orderId;
    }
}

