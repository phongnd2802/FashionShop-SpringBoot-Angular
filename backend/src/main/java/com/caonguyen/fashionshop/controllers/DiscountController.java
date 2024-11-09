package com.caonguyen.fashionshop.controllers;

import com.caonguyen.fashionshop.dtos.request.discount.ActiveDiscountRequest;
import com.caonguyen.fashionshop.dtos.request.discount.CreateDiscountRequest;
import com.caonguyen.fashionshop.dtos.request.discount.GetAllDiscountWithSPURequest;
import com.caonguyen.fashionshop.dtos.request.discount.GetDiscountAmountRequest;
import com.caonguyen.fashionshop.dtos.response.discount.DiscountResponse;
import com.caonguyen.fashionshop.dtos.response.discount.GetDiscountAmountResponse;
import com.caonguyen.fashionshop.services.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {
    @Autowired
    private IDiscountService discountService;

    @PostMapping("/create")
    public ResponseEntity<DiscountResponse> createDiscount(@RequestBody CreateDiscountRequest req) {
        DiscountResponse response = discountService.createDiscount(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/spu")
    public ResponseEntity<List<DiscountResponse>> getSpuDiscounts(@ModelAttribute GetAllDiscountWithSPURequest request) {
        System.out.println("Code: "+ request.getSpuCode());
        List<DiscountResponse> response = discountService.getAllDiscountsWithSPU(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/active")
    public ResponseEntity<DiscountResponse> activeDiscount(@RequestBody ActiveDiscountRequest request) {
        DiscountResponse response = discountService.activeDiscount(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getAmount")
    public ResponseEntity<GetDiscountAmountResponse> getDiscountAmount(@RequestBody GetDiscountAmountRequest request) {
        GetDiscountAmountResponse response = discountService.getDiscountAmount(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/delete")
    public ResponseEntity<DiscountResponse> deleteDiscount(@RequestBody String code) {
        DiscountResponse response = discountService.deleteDiscount(code);
        return ResponseEntity.ok(response);
    }
}
