package com.parrino.riccardo.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.dto.InventoryRequest;

@Service
public class OrderService {
    
    private static final String TOPIC = "inventory-service";

    @Autowired
    private KafkaTemplate<String, InventoryRequest> kafkaTemplate;

    public void placeOrder(Long productId, Integer quantity) {
        String collaborationIdUUID = UUID.randomUUID().toString();
        InventoryRequest inventoryRequest = InventoryRequest.builder()
            .productId(productId)
            .quantity(quantity)
            .orderId(1l)
            .collaborationId(collaborationIdUUID)
        .build();

        kafkaTemplate.send(TOPIC, collaborationIdUUID, inventoryRequest);
        System.out.println("Inventory request sent for product: " + String.valueOf(productId) + " with quantity: " + quantity);
    }

}
