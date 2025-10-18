package com.parrino.riccardo.inventory.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.inventory.dto.InventoryRequest;

@Service
public class OrderRequestConsumer {
    
    @KafkaListener(topics = "inventory-service", groupId = "inventory-consumer-group")
    public void consumeInventoryRequest(InventoryRequest inventoryRequest) {
        System.out.println(inventoryRequest.getOrderId());
    }

}
