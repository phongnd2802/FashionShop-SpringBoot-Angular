package com.caonguyen.fashionshop.dtos.request.product;

import lombok.Data;

@Data
public class DeleteProductRequest {
    private String spuCode = null;
    private boolean spuIsDeleted = false;
    private String skuCode = null;
    private boolean skuIsDeleted = false;
}
