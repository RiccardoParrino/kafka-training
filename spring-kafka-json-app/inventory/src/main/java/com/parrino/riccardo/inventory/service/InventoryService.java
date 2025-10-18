package com.parrino.riccardo.inventory.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.dto.InventoryRequest;

@Service
public class InventoryService {
    
    @KafkaListener(topics = "inventory-service", groupId = "inventory-group")
    public void inventoryService (InventoryRequest inventoryRequest) {
        System.out.println("Received: ");
        System.out.println("Order: " + inventoryRequest.getOrderId());
        System.out.println("Product: " + inventoryRequest.getProductId());
        System.out.println("Quantity: " + inventoryRequest.getQuantity());
        System.out.println("CollaborationId: " + inventoryRequest.getCollaborationId());
    }

}
