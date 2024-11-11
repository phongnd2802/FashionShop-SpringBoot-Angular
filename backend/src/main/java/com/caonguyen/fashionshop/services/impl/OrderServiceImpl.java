package com.caonguyen.fashionshop.services.impl;

import com.caonguyen.fashionshop.dtos.request.orders.CreateOrderRequset;
import com.caonguyen.fashionshop.dtos.response.orders.CreateOrderRes;
import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import com.caonguyen.fashionshop.entities.discount.DiscountEntity;
import com.caonguyen.fashionshop.entities.order.OrderEntity;
import com.caonguyen.fashionshop.entities.order.OrderSKUEntity;
import com.caonguyen.fashionshop.entities.payment.PaymentEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import com.caonguyen.fashionshop.repositories.*;
import com.caonguyen.fashionshop.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderSKURepository orderSkuRepository;

    @Autowired
    private SKURepository skuRepository;

    @Autowired
    private SPURepository spuRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Transactional
    @Override
    public CreateOrderRes createOrder(CreateOrderRequset req) {
        AccountEntity accountEntity = accountRepository.findByEmail(req.getEmail());
        if (accountEntity != null) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(req.getOrderId());
            orderEntity.setAccount(accountEntity);
            orderEntity.setCustomerName(req.getName());
            orderEntity.setCustomerPhoneNumber(req.getPhone());
            orderEntity.setAddress(req.getAddress());
            orderEntity.setStatus("Chờ vận chuyển");
            orderEntity.setTotalPrice(req.getTotalPrice());
            orderEntity.setShippingFee(req.getShippingFee());

            List<DiscountEntity> listDiscount = new ArrayList<>();
            for (String discountItem : req.getDiscountCode()) {
                DiscountEntity discountEntity = discountRepository.findByCode(discountItem);
                if (discountEntity != null) {
                    listDiscount.add(discountEntity);
                } else {
                    CreateOrderRes createOrderRes = new CreateOrderRes();
                    createOrderRes.setSuccess(Boolean.FALSE);
                    createOrderRes.setMessage("Không tìm thấy voucher!");
                    return createOrderRes;
                }
            }
            orderEntity.setDiscountList(listDiscount);

            PaymentEntity paymentEntity = paymentRepository.findById(req.getPaymentId()).orElse(null);
            if (paymentEntity != null) {
                orderEntity.setPayment(paymentEntity);
            } else {
                CreateOrderRes createOrderRes = new CreateOrderRes();
                createOrderRes.setSuccess(Boolean.FALSE);
                createOrderRes.setMessage("Sai phương thức thanh toán!");
                return createOrderRes;
            }

            List<OrderSKUEntity> listOrder = new ArrayList<>();
            for (String skuItem : req.getSkuList()) {
                SKUEntity skuEntity = skuRepository.findByCode(skuItem);
                if (skuEntity != null) {
                    OrderSKUEntity orderSKUEntity = new OrderSKUEntity();
                    orderSKUEntity.setOrder(orderEntity);
                    orderSKUEntity.setSku(skuEntity);
                    listOrder.add(orderSKUEntity);
                    orderSkuRepository.save(orderSKUEntity);
                } else {
                    CreateOrderRes createOrderRes = new CreateOrderRes();
                    createOrderRes.setSuccess(Boolean.FALSE);
                    createOrderRes.setMessage("Không tìm thấy sản phẩm!");
                    return createOrderRes;
                }
            }
            orderEntity.setOrderSkuList(listOrder);
            orderRepository.save(orderEntity);

            CreateOrderRes createOrderRes = new CreateOrderRes();
            createOrderRes.setSuccess(Boolean.TRUE);
            createOrderRes.setMessage("Thành công!");
            return createOrderRes;
        } else {
            CreateOrderRes createOrderRes = new CreateOrderRes();
            createOrderRes.setSuccess(Boolean.FALSE);
            createOrderRes.setMessage("Chưa có tài khoản!");
            return createOrderRes;
        }
    }
}
