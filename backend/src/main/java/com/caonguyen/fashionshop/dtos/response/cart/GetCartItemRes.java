package com.caonguyen.fashionshop.dtos.response.cart;

import com.caonguyen.fashionshop.dtos.response.product.SearchSKURes;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetCartItemRes {
    private Long id;
    private Long cartId;
    private int quantity;
    private SearchSKURes sku;
}
