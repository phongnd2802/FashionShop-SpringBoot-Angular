package com.caonguyen.fashionshop.dtos.response.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UpdateProductRes {
    private boolean success;
    private String message;
}
