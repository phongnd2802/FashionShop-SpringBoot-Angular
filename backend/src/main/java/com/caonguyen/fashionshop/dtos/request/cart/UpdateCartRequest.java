package com.caonguyen.fashionshop.dtos.request.cart;

import lombok.Data;

@Data
public class UpdateCartRequest {
    private Long cartId;
    private String skuCode;
    private boolean edit;
    private int quantity;
}
