package com.caonguyen.fashionshop.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "verify")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "verify_email")
    private String email;

    @Column(name = "verify_otp")
    private String otp;

    @Column(name = "verify_purpose")
    private int purpose;

    @Column(name = "is_verified")
    private boolean verified;

    @Column(name = "is_deleted")
    private boolean deleted;
}
