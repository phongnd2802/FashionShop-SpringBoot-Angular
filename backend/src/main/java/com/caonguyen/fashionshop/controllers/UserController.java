package com.caonguyen.fashionshop.controllers;
import com.caonguyen.fashionshop.dtos.request.user.RegisterRequest;
import com.caonguyen.fashionshop.dtos.request.user.SetPasswordRequest;
import com.caonguyen.fashionshop.dtos.request.user.VerifyOTPRequest;
import com.caonguyen.fashionshop.dtos.response.user.RegisterRes;
import com.caonguyen.fashionshop.dtos.response.user.SetPasswordRes;
import com.caonguyen.fashionshop.dtos.response.user.VerifyOTPRes;
import com.caonguyen.fashionshop.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterRes> register(@RequestBody @Valid RegisterRequest req) {
        RegisterRes response = userService.Register(req);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify_otp")
    public ResponseEntity<VerifyOTPRes> verifyOTP(@RequestBody @Valid VerifyOTPRequest req) {
        VerifyOTPRes response = userService.VerifyOTP(req);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/set_password")
    public  ResponseEntity<SetPasswordRes> setPassword(@RequestBody SetPasswordRequest req) {
        SetPasswordRes response = userService.SetPasswordRegister(req);
        return ResponseEntity.ok(response);
    }

}
