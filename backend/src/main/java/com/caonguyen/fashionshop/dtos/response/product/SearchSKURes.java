package com.caonguyen.fashionshop.dtos.response.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SearchSKURes {
    private String code;
    private String color;
    private int currentPrice;
    private int originalPrice;
    private String size;
    private int stock;
    private boolean isDeleted;
    private List<String> images;
}
