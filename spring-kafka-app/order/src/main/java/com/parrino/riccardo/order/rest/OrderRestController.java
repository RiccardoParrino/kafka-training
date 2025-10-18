package com.parrino.riccardo.order.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.order.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class OrderRestController {
    
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/api/order/place/{message}")
    public ResponseEntity<String> placeOrder(@PathVariable String message) {
        inventoryService.placeOrder(message);
        return ResponseEntity.ok("done");
    }
    
    
}
