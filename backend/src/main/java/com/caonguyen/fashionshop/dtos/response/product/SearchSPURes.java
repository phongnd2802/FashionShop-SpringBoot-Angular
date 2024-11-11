package com.caonguyen.fashionshop.dtos.response.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchSPURes {
    private String code;
    private String name;
    private String description;
    private int currentPrice;
    private int originalPrice;
    private boolean isPublished;
    private int sort;
    private boolean status;
    private String thumb;
    private int categoryId;

    public void setIsPublished(boolean published) {
        this.isPublished = published;
    }
}
