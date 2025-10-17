package com.parrino.riccardo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderProducer orderProducer;

    public Boolean placeOrder(Long productId) {
        this.orderProducer.verifyInventory(productId);
        return true;
    }

}
