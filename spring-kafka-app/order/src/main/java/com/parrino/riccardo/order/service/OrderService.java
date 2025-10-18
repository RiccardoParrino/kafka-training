package com.parrino.riccardo.order.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parrino.riccardo.order.dto.InventoryRequest;

@Service
public class OrderService {

    @Autowired
    private InventoryRequestProducer inventoryRequestProducer;
    
    public void placeOrder(Long productId, Integer quantity) {
        String uuid = UUID.randomUUID().toString();

        InventoryRequest inventoryRequest = InventoryRequest.builder()
            .orderId(1l)
            .productId(productId)
            .quantity(quantity)
            .correlationId(uuid)
        .build();

        inventoryRequestProducer.sendInventoryRequest(inventoryRequest);
    }

}
