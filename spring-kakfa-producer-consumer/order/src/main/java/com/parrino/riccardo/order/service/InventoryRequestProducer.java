package com.parrino.riccardo.order.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.order.dto.InventoryRequest;

@Service
public class InventoryRequestProducer {
    
    private final KafkaTemplate<String, InventoryRequest> kafkaTemplate;

    public InventoryRequestProducer(KafkaTemplate<String, InventoryRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryRequest(InventoryRequest request) {
        kafkaTemplate.send("inventory-request", request.getCorrelationId(), request);
    }

}
