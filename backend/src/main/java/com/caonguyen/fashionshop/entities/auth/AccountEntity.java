package com.caonguyen.fashionshop.entities.auth;

import com.caonguyen.fashionshop.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity extends BaseEntity {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_email", nullable = false, unique = true)
    private String email;

    @Column(name = "account_password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "account_email", referencedColumnName = "verify_email", insertable=false, updatable=false)
    private VerifyEntity verify;

    @OneToOne
    @JoinColumn(name = "account_role", referencedColumnName = "role_id", insertable=false, updatable=false)
    private RoleEntity role;

}
