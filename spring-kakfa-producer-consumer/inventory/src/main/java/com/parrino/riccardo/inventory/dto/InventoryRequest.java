package com.parrino.riccardo.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventoryRequest {
    private String orderId;
    private String productId;
    private int quantity;
    private String correlationId;
}
