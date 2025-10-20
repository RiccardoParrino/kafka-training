package com.parrino.riccardo.order.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.dto.InventoryRequest;
import com.parrino.riccardo.dto.InventoryResponse;

@Service
public class OrderService {

    Map<String, CompletableFuture<InventoryResponse>> responseFutures = new ConcurrentHashMap<>();

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

        CompletableFuture<InventoryResponse> future = new CompletableFuture<>();
        responseFutures.put(collaborationIdUUID, future);

        kafkaTemplate.send("inventory-request", collaborationIdUUID, inventoryRequest);       
    }

    @KafkaListener(topics = "inventory-response", groupId = "inventory-response-consumer")
    public void handleInventoryResponse (Object message) {

        if (message instanceof InventoryRequest) {
            System.out.println("InvetoryRequest message");
        } 

        if (message instanceof InventoryResponse) {
            System.out.println("InventoryResponse message");
            InventoryResponse inventoryResponse = (InventoryResponse) message;
            CompletableFuture<InventoryResponse> future = responseFutures.remove(inventoryResponse.getCollaborationId());
            if (future != null)
                future.complete(inventoryResponse);
        }

    }

}
