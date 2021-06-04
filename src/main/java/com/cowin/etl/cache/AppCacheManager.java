package com.cowin.etl.cache;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AppCacheManager {

    public static HashMap<String, String> mobileTxnIdMap = new HashMap<>();
    public static HashMap<String, String> txnIdOtpMap = new HashMap<>();
    public static HashMap<String, String> otpSHA256OtpMap = new HashMap<>();
    public static HashMap<String, String> txnIdTokenMap = new HashMap<>();

    public static Map<String, String> updateTxnId(String mobile, String txnId) {
        mobileTxnIdMap.put(mobile, txnId);
        return mobileTxnIdMap;
    }

    public static Map<String, String> updateOtp(String txnId, String otp) {
        txnIdOtpMap.put(txnId, otp);
        return txnIdOtpMap;
    }

    public static Map<String, String> updateSHA256Otp(String txnId, String otp) {
        otpSHA256OtpMap.put(txnId, otp);
        return otpSHA256OtpMap;
    }

    public static Map<String, String> updateToken(String txnId, String token) {
        txnIdTokenMap.put(txnId, token);
        return txnIdTokenMap;
    }
}
