package com.caonguyen.fashionshop.dtos.request.product;

import lombok.Data;

@Data
public class SearchSPURequest {
    private String name = null;
    private String price = null;
}
