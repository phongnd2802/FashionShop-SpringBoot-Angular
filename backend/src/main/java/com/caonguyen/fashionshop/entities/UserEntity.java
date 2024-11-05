package com.caonguyen.fashionshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user")
public class UserEntity {
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
}
