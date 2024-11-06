package com.caonguyen.fashionshop.entities.payment;

import com.caonguyen.fashionshop.entities.BaseEntity;
import com.caonguyen.fashionshop.entities.order.OrderEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity extends BaseEntity {
    @Id
    @Column(name = "payment_id")
    private int id;

    @Column(name = "payment_name")
    private String name;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    private List<OrderEntity> orderList = new ArrayList<>();
}
