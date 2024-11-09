package com.caonguyen.fashionshop.services.impl;

import com.caonguyen.fashionshop.dtos.request.discount.*;
import com.caonguyen.fashionshop.dtos.response.discount.DiscountResponse;
import com.caonguyen.fashionshop.dtos.response.discount.GetDiscountAmountResponse;
import com.caonguyen.fashionshop.entities.discount.DiscountEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import com.caonguyen.fashionshop.entities.product.SPUEntity;
import com.caonguyen.fashionshop.exceptions.BadRequestException;
import com.caonguyen.fashionshop.exceptions.NotFoundException;
import com.caonguyen.fashionshop.repositories.DiscountRepository;
import com.caonguyen.fashionshop.repositories.SKURepository;
import com.caonguyen.fashionshop.repositories.SPURepository;
import com.caonguyen.fashionshop.services.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements IDiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private SPURepository spuRepository;

    @Autowired
    private SKURepository skuRepository;

    @Override
    public DiscountResponse createDiscount(CreateDiscountRequest req) {
        // Get params
        String code = req.getCode();
        String name = req.getName();
        String description = req.getDescription();
        String type = req.getType();
        int value = req.getValue();
        LocalDateTime startTime = req.getStartTime();
        LocalDateTime endTime = req.getEndTime();
        int minOrderValue = req.getMinOrderValue();
        int maxUses = req.getMaxUses();
        List<Long> spuIds = req.getSpuIds();

        // Check
        if (startTime.isBefore(LocalDateTime.now()) || LocalDateTime.now().isAfter(endTime)) {
            throw new BadRequestException("Discount code has expired");
        }
        if (endTime.isBefore(startTime)) {
            throw new BadRequestException("Start date must before end date");
        }

        List<SPUEntity> spuList = spuRepository.findAllById(spuIds);

        DiscountEntity foundDiscount = discountRepository.findByCode(code);
        if (foundDiscount != null) {
            throw new BadRequestException("Discount code already exists");
        }

        // Save To DB
        DiscountEntity newDiscount = new DiscountEntity();
        newDiscount.setCode(code);
        newDiscount.setName(name);
        newDiscount.setDescription(description);
        newDiscount.setType(type);
        newDiscount.setValue(value);
        newDiscount.setStartTime(startTime);
        newDiscount.setEndTime(endTime);
        newDiscount.setMinOrderValue(minOrderValue);
        newDiscount.setMaxUses(maxUses);
        newDiscount.setUsesCount(0);
        newDiscount.setSpuList(spuList);
        discountRepository.save(newDiscount);

        // Response
        return convertToDiscountResponse(newDiscount);
    }

    @Override
    public DiscountResponse activeDiscount(ActiveDiscountRequest req) {
        String code = req.getCode();
        DiscountEntity foundDiscount = discountRepository.findByCode(code);
        if (foundDiscount == null) {
            throw new NotFoundException("Discount code does not exist");
        }
        foundDiscount.setActive(true);
        discountRepository.save(foundDiscount);
        return convertToDiscountResponse(foundDiscount);
    }

    @Override
    public List<DiscountResponse> getAllDiscountsWithSPU(GetAllDiscountWithSPURequest req) {
        String spuCode = req.getSpuCode();
        int limit = req.getLimit();
        int offset = req.getOffset();

        SPUEntity spuEntity = spuRepository.findByCode(spuCode);
        if (spuEntity == null) {
            throw new BadRequestException("Spu not found");
        }

        Pageable pageable = PageRequest.of(offset, limit);
        Page<DiscountEntity> discountPage = discountRepository.findActiveDiscountBySpu(spuEntity, pageable);

        return discountPage.getContent().stream()
                .map(this::convertToDiscountResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GetDiscountAmountResponse getDiscountAmount(GetDiscountAmountRequest req) {
        String code = req.getCode();
        List<SKU> skus = req.getSkus();

        DiscountEntity foundDiscount = discountRepository.findByCode(code);
        if (foundDiscount == null) {
            throw new NotFoundException("Discount code does not exist");
        }
        if (!foundDiscount.isActive()) {
            throw new NotFoundException("Discount code has expired");
        }
        if (foundDiscount.getMaxUses() <= foundDiscount.getUsesCount()) {
            throw new NotFoundException("Discount are out");
        }

//        if (LocalDateTime.now().isBefore(foundDiscount.getStartTime()) || LocalDateTime.now().isAfter(foundDiscount.getEndTime())) {
//            throw new NotFoundException("Discount code has expired");
//        }

        int totalOrder = 0;
        if (foundDiscount.getMinOrderValue() > 0) {
            for (int i = 0; i < skus.size(); i++) {
                String spkCode = skus.get(i).getSkuCode();
                int quantity = skus.get(i).getQuantity();

                SKUEntity skuEntity = skuRepository.findByCode(spkCode);
                if (skuEntity != null) {
                    totalOrder += quantity * skuEntity.getCurrentPrice();
                }
            }
            if (totalOrder < foundDiscount.getMinOrderValue()) {
                throw new BadRequestException("Discount requires a minimum order value " + foundDiscount.getMinOrderValue());
            }
        }

        String discountType = foundDiscount.getType();
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal discountValue = BigDecimal.valueOf(foundDiscount.getValue());
        BigDecimal totalOrderDecimal = BigDecimal.valueOf(totalOrder);

        if (discountType.equals("fixed_amount")) {
            amount = discountValue;
        } else {
            // Chiết khấu theo tỷ lệ phần trăm
            BigDecimal discountPercent = discountValue.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            amount = totalOrderDecimal.multiply(discountPercent);
        }

        // Response
        GetDiscountAmountResponse response = new GetDiscountAmountResponse();
        response.setTotalOrder(totalOrderDecimal);
        response.setAmount(amount.setScale(0, RoundingMode.CEILING));
        response.setTotalPrice(totalOrderDecimal.subtract(amount).setScale(0, RoundingMode.CEILING));

        return response;
    }

    @Override
    public DiscountResponse deleteDiscount(String code) {
        DiscountEntity foundDiscount = discountRepository.findByCode(code);
        if (foundDiscount == null) {
            throw new NotFoundException("Discount code does not exist");
        }
        foundDiscount.setActive(false);
        discountRepository.save(foundDiscount);
        return convertToDiscountResponse(foundDiscount);
    }


    private List<Long> getSpuIds(List<SPUEntity> spuList)  {
        return spuList.stream().map(SPUEntity::getId).collect(Collectors.toList());
    }

    private DiscountResponse convertToDiscountResponse(DiscountEntity discount) {
        DiscountResponse res = new DiscountResponse();
        res.setCode(discount.getCode());
        res.setName(discount.getName());
        res.setDescription(discount.getDescription());
        res.setType(discount.getType());
        res.setValue(discount.getValue());
        res.setStartTime(discount.getStartTime());
        res.setEndTime(discount.getEndTime());
        res.setMinOrderValue(discount.getMinOrderValue());
        res.setMaxUses(discount.getMaxUses());
        res.setUsesCount(discount.getUsesCount());
        res.setSpuIds(getSpuIds(discount.getSpuList()));
        res.setCreateAt(discount.getCreatedAt());
        res.setUpdateAt(discount.getUpdatedAt());
        return res;
    }
}
