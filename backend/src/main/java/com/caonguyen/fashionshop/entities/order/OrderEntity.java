package com.caonguyen.fashionshop.entities.order;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import com.caonguyen.fashionshop.entities.discount.DiscountEntity;
import com.caonguyen.fashionshop.entities.payment.PaymentEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends BaseEntity {

    @Id
    @Column(name = "order_id", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_customerId", referencedColumnName = "account_email", nullable = false)
    private AccountEntity account;

    @Column(name = "order_customerName", nullable = false)
    private String customerName;

    @Column(name = "order_customerPhoneNumber", nullable = false)
    private String customerPhoneNumber;

    @Column(name = "order_address", nullable = false)
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_sku",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "sku_code")
    )
    private List<SKUEntity> skuList = new ArrayList<>();

    @Column(name = "order_totalPrice", nullable = false)
    private int totalPrice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_disscount",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_code")
    )
    private List<DiscountEntity> discountList = new ArrayList<>();

    @Column(name = "order_shippingFee", nullable = false)
    private int shippingFee;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;
}
