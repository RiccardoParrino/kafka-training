package com.parrino.riccardo.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponse {
    private String orderId;
    private String productId;
    private boolean available;
    private String correlationId;
}
