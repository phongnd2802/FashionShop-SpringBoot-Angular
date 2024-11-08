package com.caonguyen.fashionshop.dtos.request.user;


import lombok.Data;

@Data
public class SetPasswordRequest {
    private String token;
    private String password;
}
