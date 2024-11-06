package com.caonguyen.fashionshop.entities.evaluation;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import com.caonguyen.fashionshop.entities.order.OrderEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "evaluation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationEntity extends BaseEntity {
    @Id
    @Column(name = "evaluation_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_customerId", referencedColumnName = "account_email", nullable = false)
    private AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_skuCode", referencedColumnName = "sku_code", nullable = false)
    private SKUEntity sku;

    @Column(name = "evaluation_star")
    private double star;

    @Column(name = "evaluation_description")
    private String description;
}
