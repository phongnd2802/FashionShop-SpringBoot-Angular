package com.caonguyen.fashionshop.entities.order;

import com.caonguyen.fashionshop.entities.product.SKUEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_sku")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSKUEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_sku_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne()
    @JoinColumn(name = "sku_code", referencedColumnName = "sku_code", nullable = false)
    private SKUEntity sku;
}
