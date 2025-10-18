package com.parrino.riccardo.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventoryRequest {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String correlationId;
}
