package com.caonguyen.fashionshop.dtos.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VerifyOTPRequest {
    @Email(message = "Email không hợp lệ")
    private String email;

    private String otp;
}
