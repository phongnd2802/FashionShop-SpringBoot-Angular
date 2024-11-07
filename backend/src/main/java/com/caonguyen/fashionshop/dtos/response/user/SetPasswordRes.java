package com.caonguyen.fashionshop.dtos.response.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetPasswordRes {
    private String userEmail;
    private String userName;
}
