package com.caonguyen.fashionshop.dtos.request.user;


import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterRequest {
    @Email(message = "Email không hợp lệ")
    private String email;
}

