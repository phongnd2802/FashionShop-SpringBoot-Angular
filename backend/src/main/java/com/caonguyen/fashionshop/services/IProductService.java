package com.caonguyen.fashionshop.services;

import com.caonguyen.fashionshop.dtos.request.product.*;
import com.caonguyen.fashionshop.dtos.response.product.*;

import java.util.List;

public interface IProductService {
    CreateProductRes createProduct(CreateProductRequest req);
    UpdateProductRes updateProduct(UpdateProductRequest req);
    DeleteProductRes deleteProduct(DeleteProductRequest req);
    List<SearchSPURes> searchSPU(SearchSPURequest req);
    List<SearchSKURes> searchSKU(SearchSKURequest req);
}
