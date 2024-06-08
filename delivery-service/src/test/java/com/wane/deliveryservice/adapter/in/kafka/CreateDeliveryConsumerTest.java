package com.wane.deliveryservice.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.deliveryservice.application.port.in.CreateDeliveryCommand;
import com.wane.deliveryservice.application.port.in.CreateDeliveryUseCase;
import com.wane.exception.CustomException;
import com.wane.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.test.context.EmbeddedKafka;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@EmbeddedKafka(topics = "create-delivery")
class CreateDeliveryConsumerTest {

    @Mock
    private CreateDeliveryUseCase createDeliveryUseCase;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CreateDeliveryConsumer createDeliveryConsumer;

    @DisplayName("카프카 메세지를 받아서 배송 생성을 요청한다.")
    @Test
    void createDelivery() throws JsonProcessingException {
        // Arrange
        String kafkaConsumeMessage = "message";
        CreateDeliveryCommand command = new CreateDeliveryCommand(1L, 1L, "2024010100001");

        given(objectMapper.readValue(kafkaConsumeMessage, CreateDeliveryCommand.class)).willReturn(command);

        // Act
        createDeliveryConsumer.createDelivery(kafkaConsumeMessage);

        // Assert
        verify(createDeliveryUseCase, times(1)).createDelivery(command);
        verify(objectMapper, times(1)).readValue(kafkaConsumeMessage, CreateDeliveryCommand.class);
    }

    @DisplayName("카프카 메세지를 받아서 배송 생성을 요청할 때 JsonProcessingException이 발생하면 CustomException을 던진다.")
    @Test
    void createDelivery_jsonProcessingException() throws Exception {
        // Arrange
        String message = "invalid json";

        when(objectMapper.readValue(any(String.class), eq(CreateDeliveryCommand.class))).thenThrow(JsonProcessingException.class);

        // Act & Assert
        assertThatThrownBy(() -> createDeliveryConsumer.createDelivery(message))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.OBJECT_MAPPER_READ_VALUE_FAILED);

        verify(objectMapper, times(1)).readValue(message, CreateDeliveryCommand.class);
        verify(createDeliveryUseCase, times(0)).createDelivery(any(CreateDeliveryCommand.class));
    }

}