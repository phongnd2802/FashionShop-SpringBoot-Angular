package com.caonguyen.fashionshop.dtos.request.discount;

import lombok.Data;

import java.util.List;

@Data
public class GetDiscountAmountRequest {
    private String code;
    private List<SKU> skus;
}
