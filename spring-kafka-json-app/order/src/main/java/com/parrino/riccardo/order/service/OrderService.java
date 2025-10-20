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
    
    private static final String TOPIC = "inventory-service";
    private static final String GROUP = "inventory-group";

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

        kafkaTemplate.send(TOPIC, collaborationIdUUID, inventoryRequest);
        
        try {
            InventoryResponse inventoryResponse = future.get(5, TimeUnit.SECONDS);

            if (inventoryResponse.getAvailable()) {
                System.out.println("Availability true. You can proceed with the payment!");
            } else {
                System.out.println("Product not available. Reduce the quantity or try with another product.");
            }
        } catch (Exception e) {
            System.out.println("Timeout or error: the inventory is temporarily unreacheable. Try a little bit later!");
        } finally {
            responseFutures.remove(inventoryRequest.getCollaborationId());
        }
        
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void handleInventoryResponse (InventoryResponse inventoryResponse) {
        CompletableFuture<InventoryResponse> future = responseFutures.remove(inventoryResponse.getCollaborationId());
        if (future != null)
            future.complete(inventoryResponse);
    }

}
