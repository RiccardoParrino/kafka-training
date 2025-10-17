package com.parrino.riccardo.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryProducer {
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    

}
