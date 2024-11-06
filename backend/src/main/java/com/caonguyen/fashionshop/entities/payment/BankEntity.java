package com.caonguyen.fashionshop.entities.payment;

import com.caonguyen.fashionshop.entities.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank")
public class BankEntity extends BaseEntity {
    @Id
    @Column(name = "bank_accNumber")
    private String bankAccNumber;

    @Column(name = "bank_qrCode", nullable = false)
    private String qrCode;
}
