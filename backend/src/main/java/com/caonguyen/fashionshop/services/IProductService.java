package com.caonguyen.fashionshop.services;

import com.caonguyen.fashionshop.dtos.request.product.ProductRequest;
import com.caonguyen.fashionshop.dtos.response.product.ProductRes;

public interface IProductService {
    ProductRes CreateProduct(ProductRequest req);
    ProductRes UpdateProduct(ProductRequest req);
    ProductRes DeleteProduct(ProductRequest req);

}
