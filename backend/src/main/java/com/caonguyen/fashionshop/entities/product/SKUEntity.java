package com.caonguyen.fashionshop.entities.product;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.evaluation.EvaluationEntity;
import com.caonguyen.fashionshop.entities.order.OrderEntity;
import com.caonguyen.fashionshop.entities.cart.CartEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product_sku")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SKUEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku_code", nullable = false, unique = true)
    private String code;

    @Column(name = "sku_current_price")
    private int currentPrice;

    @Column(name = "sku_original_price", nullable = false)
    private int originalPrice;

    @Column(name = "sku_color")
    private String color;

    @Column(name = "sku_size")
    private String size;

    @Column(name = "sku_stock", nullable = false)
    private int stock;

    @ElementCollection
    @CollectionTable(name = "sku_images", joinColumns = @JoinColumn(name = "sku_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "spu_code", referencedColumnName = "spu_code", nullable = false)
    private SPUEntity spu;

    @ManyToMany(mappedBy = "skuList")
    @ToString.Exclude
    private List<OrderEntity> orderList = new ArrayList<>();

    @ManyToMany(mappedBy = "skuList")
    @ToString.Exclude
    private List<CartEntity> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "sku")
    private List<EvaluationEntity> evaluationList = new ArrayList<>();
}
