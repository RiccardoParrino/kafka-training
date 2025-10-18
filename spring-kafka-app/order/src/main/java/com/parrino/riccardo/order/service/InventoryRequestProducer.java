package com.parrino.riccardo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.order.dto.InventoryRequest;

@Service
public class InventoryRequestProducer {
    
    private static final String TOPIC = "inventory-service";

    @Autowired
    private KafkaTemplate<String, InventoryRequest> kafkaTemplate;

    public void sendInventoryRequest(InventoryRequest inventoryRequest) {
        kafkaTemplate.send(TOPIC, inventoryRequest);
    }

}
