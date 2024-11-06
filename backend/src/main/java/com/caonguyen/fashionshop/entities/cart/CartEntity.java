package com.caonguyen.fashionshop.entities.cart;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import com.caonguyen.fashionshop.entities.product.SKUEntity;
import com.caonguyen.fashionshop.entities.product.SPUEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity extends BaseEntity {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cart_state", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'active'")
    private String state;

    @Column(name = "cart_count_product", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int countProduct;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    private AccountEntity account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_skus",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "sku_code")
    )
    private List<SKUEntity> skuList = new ArrayList<>();

}
