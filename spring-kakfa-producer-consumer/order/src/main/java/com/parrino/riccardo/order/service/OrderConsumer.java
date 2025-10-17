package com.parrino.riccardo.order.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "orders", groupId = "my-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received order: " + record.value());
    }

}
