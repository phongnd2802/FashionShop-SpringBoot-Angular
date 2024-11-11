package com.caonguyen.fashionshop.dtos.response.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateCartRes {
    private boolean success;
    private String message;
}
