package com.caonguyen.fashionshop.dtos.response.discount;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class DiscountResponse {
    private String code;
    private String name;
    private String description;
    private String type;
    private int value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int minOrderValue;
    private int maxUses;
    private int usesCount;
    private List<Long> spuIds;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
