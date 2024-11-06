package com.caonguyen.fashionshop.entities.discount;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.product.SPUEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountEntity extends BaseEntity {
    @Id
    @Column(name = "discount_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount_name", nullable = false)
    private String name;

    @Column(name = "discount_description")
    private String description;

    @Column(name = "discount_type", nullable = false)
    private String type;

    @Column(name = "discount_value", nullable = false)
    private int value;

    @Column(name = "discount_code", nullable = false, unique = true)
    private String code;

    @Column(name = "discount_start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "discount_end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "discount_max_uses", nullable = false)
    private int maxUses; // so luong duoc su dung discoun nay

    @Column(name = "discount_uses_count", nullable = false)
    private int usesCount; // da duoc su dung bao nhieu

    @Column(name = "discount_min_order_value", nullable = false)
    private int minOrderValue;

    @Column(name = "discount_is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "discount_spus",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "spu_id")
    )
    private List<SPUEntity> spuList = new ArrayList<>();
}
