package com.caonguyen.fashionshop.dtos.request.orders;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequset {
    private String orderId;
    private String email;
    private String name;
    private String phone;
    private String address;
    private int shippingFee;
    private int totalPrice;
    private int paymentId;
    private List<String> skuList;
    private List<String> discountCode;
}
