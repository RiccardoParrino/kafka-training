package com.parrino.riccardo.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.dto.InventoryRequest;
import com.parrino.riccardo.dto.InventoryResponse;

@Service
public class InventoryService {

    @Autowired
    private KafkaTemplate<String, InventoryResponse> kafkaTemplate;

    @KafkaListener(topics = "inventory-request", groupId = "inventory-request-consumer")
    public void inventoryService (InventoryRequest inventoryRequest) {
        System.out.println("Received: ");
        System.out.println("Order: " + inventoryRequest.getOrderId());
        System.out.println("Product: " + inventoryRequest.getProductId());
        System.out.println("Quantity: " + inventoryRequest.getQuantity());
        System.out.println("CollaborationId: " + inventoryRequest.getCollaborationId());

        // Processing the request
        InventoryResponse inventoryResponse = InventoryResponse.builder()
            .orderId(inventoryRequest.getOrderId())
            .productId(inventoryRequest.getProductId())
            .available(checkAvailability(inventoryRequest.getProductId(), inventoryRequest.getQuantity()))
            .collaborationId(inventoryRequest.getCollaborationId())
        .build();

        kafkaTemplate.send("inventory-response", inventoryRequest.getCollaborationId(), inventoryResponse);
    }

    private boolean checkAvailability (Long productId, Integer quantity) {
        if (quantity <= 5)
            return true;
        return false;
    }

}
