package com.caonguyen.fashionshop.dtos.response.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GetCartRes {
    private Long cartid;
    private int countProduct;
    private List<GetCartItemRes> items;
}
