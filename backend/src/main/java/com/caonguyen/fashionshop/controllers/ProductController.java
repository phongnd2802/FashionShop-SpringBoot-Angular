package com.caonguyen.fashionshop.controllers;

import com.caonguyen.fashionshop.dtos.request.product.*;
import com.caonguyen.fashionshop.dtos.response.product.*;
import com.caonguyen.fashionshop.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<CreateProductRes> createProduct(
            @RequestParam("productData") String productDataJson,
            @RequestParam("fileThumb") MultipartFile fileThumb,
            @RequestParam("fileImages") List<MultipartFile> fileImages) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateProductRequest createProductRequest = objectMapper.readValue(productDataJson, CreateProductRequest.class);
        createProductRequest.setFileThumb(fileThumb);
        createProductRequest.setFileImages(fileImages);

        CreateProductRes createProductRes = productService.createProduct(createProductRequest);
        return ResponseEntity.ok(createProductRes);
    }

    @PostMapping("/updateProduct")
    public ResponseEntity<UpdateProductRes> updateProduct(
            @RequestParam("productData") String productDataJson,
            @RequestParam("fileThumb") MultipartFile fileThumb,
            @RequestParam("fileImages") List<MultipartFile> fileImages) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        UpdateProductRequest updateProductRequest = objectMapper.readValue(productDataJson, UpdateProductRequest.class);
        updateProductRequest.setFileThumb(fileThumb);
        updateProductRequest.setFileImages(fileImages);

        UpdateProductRes updateProductRes = productService.updateProduct(updateProductRequest);
        return ResponseEntity.ok(updateProductRes);
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<DeleteProductRes> deleteProduct(
            @RequestParam("productData") String productDataJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DeleteProductRequest deleteProductRequest = objectMapper.readValue(productDataJson, DeleteProductRequest.class);

        DeleteProductRes deleteProductRes = productService.deleteProduct(deleteProductRequest);
        return ResponseEntity.ok(deleteProductRes);
    }

    @GetMapping("/searchSPU")
    public ResponseEntity<List<SearchSPURes>> searchSPU(
            @RequestParam("productData") String productDataJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SearchSPURequest searchSPURequest = objectMapper.readValue(productDataJson, SearchSPURequest.class);

        List<SearchSPURes> searchSPURes = productService.searchSPU(searchSPURequest);
        return ResponseEntity.ok(searchSPURes);
    }

    @GetMapping("/searchSKU")
    public ResponseEntity<List<SearchSKURes>> searchSKU(
            @RequestParam("spuCode") String spuCode) throws IOException {
        SearchSKURequest searchSKURequest = new SearchSKURequest();
        searchSKURequest.setSpuCode(spuCode);
        List<SearchSKURes> searchSKURes = productService.searchSKU(searchSKURequest);
        return ResponseEntity.ok(searchSKURes);
    }
}
