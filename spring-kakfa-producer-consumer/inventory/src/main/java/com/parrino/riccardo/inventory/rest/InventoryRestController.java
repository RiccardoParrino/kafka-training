package com.parrino.riccardo.inventory.rest;

import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.inventory.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class InventoryRestController {

    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping("/api/inventory/verify/{productId}")
    public String verifyOrder(@PathVariable Long productId) {
        return 
    }
    

}
