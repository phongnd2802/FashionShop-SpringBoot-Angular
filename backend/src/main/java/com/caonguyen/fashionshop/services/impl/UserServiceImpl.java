package com.caonguyen.fashionshop.services.impl;

import com.caonguyen.fashionshop.dtos.request.user.*;
import com.caonguyen.fashionshop.dtos.response.user.*;
import com.caonguyen.fashionshop.services.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public RegisterRes Register(RegisterRequest req) {
        return null;
    }

    @Override
    public VerifyOTPRes VerifyOTP(VerifyOTPRequest req) {
        return null;
    }

    @Override
    public SetPasswordRes SetPasswordRegister(SetPasswordRequest req) {
        return null;
    }

    @Override
    public LoginRes Login(LoginRequest req) {
        return null;
    }

    @Override
    public boolean Logout() {
        return false;
    }

    @Override
    public UserRes UpdateProfile(UpdateProfileRequest req) {
        return null;
    }
}
