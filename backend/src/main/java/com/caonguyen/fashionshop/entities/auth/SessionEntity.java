package com.caonguyen.fashionshop.entities.auth;

import com.caonguyen.fashionshop.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "session")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionEntity extends BaseEntity {
    @Id
    @Column(name = "session_id")
    private UUID id;

    @Column(name = "session_refresh_token", nullable = false)
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "")
    private AccountEntity account;
}
