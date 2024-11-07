package com.caonguyen.fashionshop.services.impl;

import com.caonguyen.fashionshop.dtos.request.user.*;
import com.caonguyen.fashionshop.dtos.response.user.*;
import com.caonguyen.fashionshop.entities.auth.AccountEntity;
import com.caonguyen.fashionshop.entities.auth.ProfileEntity;
import com.caonguyen.fashionshop.entities.auth.RoleEntity;
import com.caonguyen.fashionshop.entities.auth.VerifyEntity;
import com.caonguyen.fashionshop.exceptions.BadRequestException;
import com.caonguyen.fashionshop.exceptions.ConflictException;
import com.caonguyen.fashionshop.exceptions.NotFoundException;
import com.caonguyen.fashionshop.exceptions.UnAuthorizedException;
import com.caonguyen.fashionshop.repositories.AccountRepository;
import com.caonguyen.fashionshop.repositories.ProfileRepository;
import com.caonguyen.fashionshop.repositories.RoleRepository;
import com.caonguyen.fashionshop.repositories.VerifyRepository;
import com.caonguyen.fashionshop.security.PasswordEncoder;
import com.caonguyen.fashionshop.services.IUserService;
import com.caonguyen.fashionshop.services.RedisService;
import com.caonguyen.fashionshop.utils.CryptoUtil;
import com.caonguyen.fashionshop.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private VerifyRepository verifyRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public RegisterRes Register(RegisterRequest req) {
        String email = req.getEmail();

        // check email exists in account table
        AccountEntity foundAccount = accountRepository.findByEmail(email);
        if (foundAccount != null) {
            throw new ConflictException("Email này đã được đăng kí");
        }

        // Check OTP
        String hashEmail = CryptoUtil.getHash(email);
        String userKey = getUserKey(hashEmail);
        Object otpFound = redisService.get(userKey);
        if (otpFound != null) {
            System.out.println(otpFound.toString());
            throw new ConflictException("Email này đã được đăng kí");
        }
        // Generate OTP
        int otpNew = RandomUtil.generateSixDigit();
        System.out.println(otpNew);
        // Save to DB
        VerifyEntity verify = new VerifyEntity();
        verify.setEmail(email);
        verify.setOtp(String.valueOf(otpNew));
        verify.setVerifyKeyHash(hashEmail);

        verifyRepository.save(verify);
        System.out.println(verify.getId());
        // Save to Redis
        redisService.set(userKey, String.valueOf(otpNew), 3, TimeUnit.MINUTES);
        // Send OTP to Email

        // Response
        RegisterRes res = new RegisterRes();
        res.setVerifyId(CryptoUtil.getHash(String.valueOf(verify.getId())));
        return res;
    }

    @Transactional
    @Override
    public VerifyOTPRes VerifyOTP(VerifyOTPRequest req) {
        String email = req.getEmail();
        String otp = req.getOtp();

        String hashEmail = CryptoUtil.getHash(email);
        // Get OTP in redis
        Object otpObj = redisService.get(getUserKey(hashEmail));
        if (otpObj == null) {
            throw new NotFoundException("OTP không hợp lệ");
        }
        String otpStr = otpObj.toString();
        if (!otpStr.equals(otp)) {
            throw new UnAuthorizedException("OTP không hợp lệ");
        }

        // update verified
        VerifyEntity verifyEntity = verifyRepository.findByEmail(email);
        verifyEntity.setVerified(true);
        verifyRepository.save(verifyEntity);

        // Response
        VerifyOTPRes res = new VerifyOTPRes();
        res.setToken(hashEmail);

        return res;
    }

    @Transactional
    @Override
    public SetPasswordRes SetPasswordRegister(SetPasswordRequest req) {
        String token = req.getToken();
        String password = req.getPassword();

        // Check Info Verify
        VerifyEntity verify = verifyRepository.findByVerifyKeyHash(token);
        if (verify == null) {
            throw new BadRequestException("token không hợp lệ");
        }

        if (!verify.isVerified()) {
            throw new UnAuthorizedException("chưa xác thực otp");
        }

        String hashPassword = passwordEncoder.encode(password);
        RoleEntity role = roleRepository.findByName("customer");
        System.out.printf("Role: %s\n", role.getName());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPassword(hashPassword);
        accountEntity.setEmail(verify.getEmail());
        accountEntity.setPassword(hashPassword);
        accountEntity.setRole(role);
        accountRepository.save(accountEntity);

        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(accountEntity.getEmail());
        profileEntity.setName(getNameFromEmail(accountEntity.getEmail()));
        profileRepository.save(profileEntity);

        // Response
        SetPasswordRes res = new SetPasswordRes();
        res.setUserEmail(profileEntity.getEmail());
        res.setUserName(profileEntity.getName());
        return res;
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

    private String getUserKey(String key) {
        return String.format("user:%s:otp", key);
    }

    private String getNameFromEmail(String email) {
        String[] parts = email.split("@");
        return parts[0];
    }
}
