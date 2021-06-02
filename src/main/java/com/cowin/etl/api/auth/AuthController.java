package com.cowin.etl.api.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController(value = "authController")
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/{mobileNumber}")
    public @ResponseBody String generateOtp(@PathVariable long mobileNumber){
        return authService.generateOtp(mobileNumber);
    }

    @GetMapping("/{txnId}/{otp}")
    public @ResponseBody String confirmOtp(@PathVariable String txnId, @PathVariable String otp){
        return authService.confirmOtp(txnId,otp);
    }


}
