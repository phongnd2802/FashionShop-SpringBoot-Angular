package com.caonguyen.fashionshop.controllers;

import com.caonguyen.fashionshop.dtos.request.cart.AddCartRequest;
import com.caonguyen.fashionshop.dtos.request.cart.GetCartRequest;
import com.caonguyen.fashionshop.dtos.request.cart.UpdateCartRequest;
import com.caonguyen.fashionshop.dtos.response.cart.AddCartRes;
import com.caonguyen.fashionshop.dtos.response.cart.GetCartRes;
import com.caonguyen.fashionshop.dtos.response.cart.UpdateCartRes;
import com.caonguyen.fashionshop.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<AddCartRes> addCart(@RequestBody AddCartRequest req) {
        AddCartRes addCartRes = cartService.addCart(req);
        return ResponseEntity.ok(addCartRes);
    }

    @PostMapping("/updateCart")
    public ResponseEntity<UpdateCartRes> updateCart(@RequestBody UpdateCartRequest req) {
        UpdateCartRes updateCartRes = cartService.updateCart(req);
        return ResponseEntity.ok(updateCartRes);
    }

    @GetMapping("/getCart")
    public ResponseEntity<GetCartRes> getCart(@RequestBody GetCartRequest req) {
        GetCartRes getCartRes = cartService.getCart(req);
        return ResponseEntity.ok(getCartRes);
    }
}
