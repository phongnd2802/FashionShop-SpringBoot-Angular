package com.caonguyen.fashionshop.services;


import com.caonguyen.fashionshop.dtos.request.orders.CreateOrderRequset;
import com.caonguyen.fashionshop.dtos.response.orders.CreateOrderRes;

public interface IOrderService {
    CreateOrderRes createOrder(CreateOrderRequset req);
}
