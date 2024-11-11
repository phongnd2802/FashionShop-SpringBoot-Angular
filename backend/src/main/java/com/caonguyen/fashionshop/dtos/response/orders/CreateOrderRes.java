package com.caonguyen.fashionshop.dtos.response.orders;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateOrderRes {
    private boolean success;
    private String message;
}
