package com.caonguyen.fashionshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity extends BaseEntity {
    @Id
    @Column(name = "profile_id", nullable = false)
    private Long id;

    @Column(name = "profile_name", length = 50, nullable = false)
    private String name;

    @Column(name = "profile_email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "profile_phoneNumber", length = 10, nullable = true)
    private String phoneNumber;

    @Column(name = "profile_birthDay", nullable = true)
    private Date birthDay;

    @Column(name = "profile_address", length = 300, nullable = false)
    private String address;

    @Column(name = "profile_avatar", length = 300, nullable = true)
    private String avatar;

    @OneToOne
    @JoinColumn(name = "profile_email", referencedColumnName = "account_email", insertable=false, updatable=false)
    private AccountEntity account;
}
