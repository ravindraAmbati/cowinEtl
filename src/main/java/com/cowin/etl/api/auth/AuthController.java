package com.cowin.etl.api.auth;

import com.cowin.etl.cache.AppCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "authController")
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AppCacheManager appCacheManager;


    @GetMapping("/mobile/{mobileNumber}")
    public @ResponseBody
    String generateOtp(@PathVariable String mobileNumber) {
        String txnId = authService.generateOtpAndGetTxnId(mobileNumber);
        return AppCacheManager.updateTxnId(mobileNumber, txnId).get(mobileNumber);
    }

    @GetMapping("/book/{mobileNumber}/{otp}")
    public @ResponseBody
    String validateOtp(@PathVariable String mobileNumber, @PathVariable String otp) {
        String txnId = AppCacheManager.mobileTxnIdMap.get(mobileNumber);
        AppCacheManager.updateOtp(txnId,otp);
        String token = authService.validateOtpAndGetToken(txnId);
        return AppCacheManager.updateToken(txnId, token).get(txnId);
    }


}
