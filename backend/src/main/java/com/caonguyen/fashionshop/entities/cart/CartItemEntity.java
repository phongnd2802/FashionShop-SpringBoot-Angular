package com.caonguyen.fashionshop.entities.cart;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "sku_code", referencedColumnName = "sku_code", nullable = false)
    private SKUEntity sku;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
