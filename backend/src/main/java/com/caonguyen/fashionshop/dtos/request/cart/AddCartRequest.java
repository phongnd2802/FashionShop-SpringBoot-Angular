package com.caonguyen.fashionshop.dtos.request.cart;

import lombok.Data;

@Data
public class AddCartRequest {
    private String email;
    private String skuCode;
}
