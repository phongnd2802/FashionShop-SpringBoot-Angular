package com.caonguyen.fashionshop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private int id;

    @Column(name = "user_name", length = 50, nullable = false)
    private String name;

    @Column(name = "user_email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "user_phoneNumber", length = 10, nullable = true)
    private String phoneNumber;

    @Column(name = "user_birthDay", nullable = true)
    private Date birthDay;

    @Column(name = "user_address", length = 300, nullable = false)
    private String address;

    @Column(name = "user_avatar", length = 300, nullable = true)
    private String avatar;

    @OneToOne
    @JoinColumn(name = "user_email")
    private AccountEntity account;
}
