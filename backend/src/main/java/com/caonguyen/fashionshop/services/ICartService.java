package com.caonguyen.fashionshop.services;

import com.caonguyen.fashionshop.dtos.request.cart.AddCartRequest;
import com.caonguyen.fashionshop.dtos.request.cart.GetCartRequest;
import com.caonguyen.fashionshop.dtos.request.cart.UpdateCartRequest;
import com.caonguyen.fashionshop.dtos.response.cart.AddCartRes;
import com.caonguyen.fashionshop.dtos.response.cart.GetCartRes;
import com.caonguyen.fashionshop.dtos.response.cart.UpdateCartRes;

public interface ICartService {
    AddCartRes addCart(AddCartRequest req);
    UpdateCartRes updateCart(UpdateCartRequest req);
    GetCartRes getCart(GetCartRequest req);
}
