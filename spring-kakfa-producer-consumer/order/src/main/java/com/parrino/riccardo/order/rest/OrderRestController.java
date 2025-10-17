package com.parrino.riccardo.order.rest;

import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.order.dto.InventoryRequest;
import com.parrino.riccardo.order.dto.InventoryResponse;
import com.parrino.riccardo.order.dto.OrderRequest;
import com.parrino.riccardo.order.service.InventoryRequestProducer;
import com.parrino.riccardo.order.service.InventoryResponseConsumer;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OrderRestController {

    @Autowired
    private InventoryRequestProducer requestProducer;

    @Autowired
    private InventoryResponseConsumer responseConsumer;
    
    @PostMapping("/api/orders/place")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        String correlationId = UUID.randomUUID().toString();
        String orderId = UUID.randomUUID().toString();

        InventoryRequest inventoryRequest = InventoryRequest.builder()
            .correlationId(correlationId)
            .orderId(orderId)
            .productId(orderRequest.getProductId())
            .quantity(orderRequest.getQuantity())
        .build();

        CompletableFuture<InventoryResponse> future = responseConsumer.registerRequest(correlationId);
        requestProducer.sendInventoryRequest(inventoryRequest);

        try {
            InventoryResponse response = future.get(60, TimeUnit.SECONDS);

            if (response.isAvailable()) {
                return ResponseEntity.ok("Stock available, proceeding to payment.");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Product not available.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body("No response from Inventory Service");
        }
    }
    

}
