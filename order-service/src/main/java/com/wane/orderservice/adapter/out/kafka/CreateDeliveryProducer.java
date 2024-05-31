package com.wane.orderservice.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wane.orderservice.application.port.out.CreateDeliveryCommand;
import com.wane.orderservice.application.port.out.CreateDeliveryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreateDeliveryProducer implements CreateDeliveryPort {

    private final KafkaTemplate<String, String> template;
    private final ObjectMapper objectMapper;

    @Override
    public void createDelivery(CreateDeliveryCommand command) {
        String jsonStringToProduce;

        try {
            jsonStringToProduce = objectMapper.writeValueAsString(command);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ProducerRecord<String, String> record = new ProducerRecord<>("create-delivery", command.orderId().toString(), jsonStringToProduce);
        try (Producer<String, String> producer = template.getProducerFactory()
                .createProducer()) {
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    log.info("Message sent successfully. offset: {} value : {} ", metadata.offset(), record.value());
                } else {
                    log.error("failed to SendMessage to Kafka :delivery + orderId = {} ", command.orderId(), exception);
                }
            });
        }
    }
}
