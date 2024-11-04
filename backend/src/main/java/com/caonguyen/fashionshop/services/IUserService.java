package com.caonguyen.fashionshop.services;

import com.caonguyen.fashionshop.dtos.request.user.*;
import com.caonguyen.fashionshop.dtos.response.user.*;

public interface IUserService {
    RegisterRes Register(RegisterRequest req);
    VerifyOTPRes VerifyOTP(VerifyOTPRequest req);
    SetPasswordRes SetPasswordRegister(SetPasswordRequest req);

    LoginRes Login(LoginRequest req);
    boolean Logout();

    UserRes UpdateProfile(UpdateProfileRequest req);
}
