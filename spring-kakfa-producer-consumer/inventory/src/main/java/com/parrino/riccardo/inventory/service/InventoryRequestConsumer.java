package com.parrino.riccardo.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.inventory.dto.InventoryRequest;
import com.parrino.riccardo.inventory.dto.InventoryResponse;

@Service
public class InventoryRequestConsumer {
    
    @Autowired
    private KafkaTemplate<String, InventoryResponse> kafkaTemplate;

    @KafkaListener(topics = "inventory-request", groupId = "inventory-service")
    public void handleInventoryCheck(InventoryRequest request) {
        boolean available = checkAvailability(request.getProductId(), request.getQuantity());

        InventoryResponse response = InventoryResponse.builder()
            .orderId(request.getOrderId())
            .productId(request.getProductId())
            .available(available)
            .correlationId(request.getCorrelationId())
        .build();

        kafkaTemplate.send("inventory-response", response.getCorrelationId(), response);
    }

    private boolean checkAvailability(String productId, int quantity) {
        return quantity <= 5;
    }

}
