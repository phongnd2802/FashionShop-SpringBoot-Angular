package com.caonguyen.fashionshop.entities.product;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.discount.DiscountEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_spu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class    SPUEntity extends BaseEntity {
    @Id
    @Column(name = "spu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "spu_code", nullable = false, unique = true)
    private String code;

    @Column(name = "spu_name", nullable = false)
    private String name;

    @Column(name = "spu_description", nullable = false)
    private String description;

    @Column(name = "spu_status", nullable = false)
    private boolean status; // false: het hang, true: con hang

    @Column(name = "spu_thumb", nullable = false)
    private String thumbnail;

    @Column(name = "spu_current_price")
    private int currentPrice;

    @Column(name = "spu_original_price", nullable = false)
    private int originalPrice;

    @Column(name = "spu_is_publish", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean published;

    @Column(name = "spu_sort", nullable = false)
    private int sort;

    @Column(name = "spu_id_Deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "spu", fetch = FetchType.LAZY)
    private List<SKUEntity> skuList = new ArrayList<>();

    @ManyToMany(mappedBy = "spuList")
    @ToString.Exclude
    private List<DiscountEntity> discountList = new ArrayList<>();

}
