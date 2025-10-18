package com.parrino.riccardo.order.rest;

import org.springframework.web.bind.annotation.RestController;

import com.parrino.riccardo.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/api/order/place/{productId}/{quantity}")
    public ResponseEntity<String> placeOrder(@PathVariable Long productId, @PathVariable Integer quantity) {
        System.out.println("Place Order for: ");
        System.out.println("ProductId: " + productId);
        System.out.println("Quantity: " + quantity);

        orderService.placeOrder(productId, quantity);
        return ResponseEntity.ok("done");
    }
    

}
