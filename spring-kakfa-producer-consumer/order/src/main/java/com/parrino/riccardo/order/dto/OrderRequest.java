package com.parrino.riccardo.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private String productId;
    private int quantity;
}
