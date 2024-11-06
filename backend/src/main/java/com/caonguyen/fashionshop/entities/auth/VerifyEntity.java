package com.caonguyen.fashionshop.entities.auth;


import com.caonguyen.fashionshop.entities.BaseEntity;
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
public class VerifyEntity extends BaseEntity {
    @Id
    @Column(name = "verify_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "verify_email", nullable = false, unique = true)
    private String email;

    @Column(name = "verify_otp", nullable = false)
    private String otp;

    @Column(name = "is_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean verified;

    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted;

    @OneToOne(mappedBy = "verify")
    private AccountEntity account;
}