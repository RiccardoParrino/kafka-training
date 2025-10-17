package com.parrino.riccardo.order.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.order.dto.InventoryResponse;

@Service
public class InventoryResponseConsumer {
    
    private final Map<String, CompletableFuture<InventoryResponse>> pendingRequest = new ConcurrentHashMap<>();

    public CompletableFuture<InventoryResponse> registerRequest(String correlationId) {
        CompletableFuture<InventoryResponse> future = new CompletableFuture<>();
        pendingRequest.put(correlationId, future);
        return future;
    }

    @KafkaListener(topics = "inventory-response", groupId = "order-service")
    public void listen(InventoryResponse response) {
        CompletableFuture<InventoryResponse> future = pendingRequest.remove(response.getCorrelationId());
        if (future != null) {
            future.complete(response);
        }
    }
}
