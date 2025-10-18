package com.parrino.riccardo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private static final String TOPIC = "inventory-service";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void placeOrder(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Message sent: " + message);
    }

}
