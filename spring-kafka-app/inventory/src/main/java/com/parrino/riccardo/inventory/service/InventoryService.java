package com.parrino.riccardo.inventory.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    
    @KafkaListener(topics = "inventory-service", groupId = "inventory-group")
    public void orderRequest (String message) {
        System.out.println("Message received: " + message);
    }

}
