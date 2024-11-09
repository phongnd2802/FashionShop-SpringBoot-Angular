package com.caonguyen.fashionshop.dtos.response.discount;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class GetDiscountAmountResponse {
    private BigDecimal totalOrder;
    private BigDecimal amount;
    private BigDecimal totalPrice;
}
