package com.caonguyen.fashionshop.dtos.request.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UpdateProductRequest {
    private String spuCode = null;
    private int spuCurrentPrice = 0;
    private int spuOriginalPrice = 0;
    private String spuName = null;
    private String spuDescription = null;
    private int spuSort = 0;
    private boolean spuStatus = true;
    private String skuCode = null;
    private int skuCurrentPrice = 0;
    private int skuOriginalPrice = 0;
    private int skuStock = 0;
    private MultipartFile fileThumb = null;
    private String spuThumb = null;
    private List<MultipartFile> fileImages = null;
    private List<String> spuImages = null;
}
