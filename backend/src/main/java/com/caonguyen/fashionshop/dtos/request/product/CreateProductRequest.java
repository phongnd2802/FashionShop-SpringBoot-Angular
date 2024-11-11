package com.caonguyen.fashionshop.dtos.request.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CreateProductRequest {
    private String categoryName;
    private int spuCurrentPrice;
    private int spuOriginalPrice;
    private String spuCode;
    private String spuName;
    private String spuDescription;
    private int spuSort = 0;
    private boolean spuStatus = true;
    private MultipartFile fileThumb;
    private String spuThumb;
    private List<MultipartFile> fileImages;
    private List<String> spuImages;
    private String skuCode;
    private String skuColor;
    private String skuSize;
    private int skuCurrentPrice;
    private int skuOriginalPrice;
    private int skuStock;
}