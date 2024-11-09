package com.caonguyen.fashionshop.dtos.request.discount;


import lombok.Data;

@Data
public class GetAllDiscountWithSPURequest {
    private String spuCode;
    private Integer limit;
    private Integer offset;
}
