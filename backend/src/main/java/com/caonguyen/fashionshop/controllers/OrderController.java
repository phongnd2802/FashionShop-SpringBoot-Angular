package com.caonguyen.fashionshop.controllers;

import com.caonguyen.fashionshop.dtos.request.orders.CreateOrderRequset;
import com.caonguyen.fashionshop.dtos.response.orders.CreateOrderRes;
import com.caonguyen.fashionshop.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<CreateOrderRes> createOrder(@RequestBody CreateOrderRequset req) {
        CreateOrderRes createOrderRes = orderService.createOrder(req);
        return ResponseEntity.ok(createOrderRes);
    }
}
