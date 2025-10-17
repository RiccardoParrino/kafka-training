package com.parrino.riccardo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {
    public String orderId;
    public String productId;
    public int quantity;
    public String correlationId;
}
