package com.wane.deliveryservice.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.deliveryservice.application.port.in.CreateDeliveryCommand;
import com.wane.deliveryservice.application.port.in.CreateDeliveryUseCase;
import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateDeliveryConsumer {

    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(groupId = "delivery-service", topics = "create-delivery")
    public void createDelivery(String message) {
        log.info("Received message: {}", message);

        try {
            CreateDeliveryCommand command = objectMapper.readValue(message, CreateDeliveryCommand.class);
            createDeliveryUseCase.createDelivery(command);
        } catch (JsonProcessingException e) {
            throw new CustomException(ErrorCode.OBJECT_MAPPER_READ_VALUE_FAILED);
        }

    }
}
