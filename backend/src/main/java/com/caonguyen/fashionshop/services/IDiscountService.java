package com.caonguyen.fashionshop.services;

import com.caonguyen.fashionshop.dtos.request.discount.ActiveDiscountRequest;
import com.caonguyen.fashionshop.dtos.request.discount.CreateDiscountRequest;
import com.caonguyen.fashionshop.dtos.request.discount.GetAllDiscountWithSPURequest;
import com.caonguyen.fashionshop.dtos.request.discount.GetDiscountAmountRequest;
import com.caonguyen.fashionshop.dtos.response.discount.DiscountResponse;
import com.caonguyen.fashionshop.dtos.response.discount.GetDiscountAmountResponse;


import java.util.List;

public interface IDiscountService {
    DiscountResponse createDiscount(CreateDiscountRequest req);
    DiscountResponse activeDiscount(ActiveDiscountRequest req);
    List<DiscountResponse> getAllDiscountsWithSPU(GetAllDiscountWithSPURequest req);
    GetDiscountAmountResponse getDiscountAmount(GetDiscountAmountRequest req);
    DiscountResponse deleteDiscount(String code);
}
