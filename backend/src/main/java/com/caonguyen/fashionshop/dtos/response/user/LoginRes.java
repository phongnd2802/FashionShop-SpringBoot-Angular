package com.caonguyen.fashionshop.dtos.response.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRes {
    private String sessionId;
    private String accessToken;
    private String refreshToken;
}
