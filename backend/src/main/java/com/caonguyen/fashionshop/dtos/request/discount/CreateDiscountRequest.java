package com.caonguyen.fashionshop.dtos.request.discount;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateDiscountRequest {
    private String code;
    private String name;
    private String description;
    private String type;
    private int value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int minOrderValue;
    private int maxUses;
    private List<Long> spuIds;

}
