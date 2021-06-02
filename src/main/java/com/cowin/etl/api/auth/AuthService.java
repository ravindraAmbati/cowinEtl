package com.cowin.etl.api.auth;

import com.cowin.etl.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Service(value = "authService")
@Slf4j
public class AuthService {

    private static final String GENERATE_OTP_URL = "https://cdn-api.co-vin.in/api/v2/auth/generateMobileOTP/";
    private static final String VALIDATE_OTP_URL = "https://cdn-api.co-vin.in/api/v2/auth/validateMobileOtp";

    @Autowired
    private HttpUtils httpUtils;

    public String generateOtp(long mobileNumber) {
        HashMap<String, Object> httpBodyMap = new HashMap<>();
        httpBodyMap.put("secret", "U2FsdGVkX1/cvm7luOFiHj/BrclRD58T/z2l+L3zar50UAlxSY3qLDxmO8fNaFnxCtQC39Qo9mTua2Og0qoKwQ==");
        httpBodyMap.put("mobile", mobileNumber);
        JSONObject jsonObject = new JSONObject(httpBodyMap);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toString(), HttpUtils.getHttpHeaders());
        String responseJson = httpUtils.getRestTemplate().postForObject(GENERATE_OTP_URL, httpEntity, String.class);
        JSONObject responseJsonObject = new JSONObject(responseJson);
        String txnId = responseJsonObject.get("txnId").toString();
        log.info("mobile: {} and txnId: {}", mobileNumber, txnId);
        return txnId;
    }

    public String confirmOtp(String txnId, String otp) {

        String encodeOtp = encodeHex(getSha256(otp));
        if (!ObjectUtils.isEmpty(encodeOtp)) {
            HashMap<String, Object> httpBodyMap = new HashMap<>();
            httpBodyMap.put("otp", encodeOtp);
            httpBodyMap.put("txnId", txnId);
            JSONObject jsonObject = new JSONObject(httpBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toString(), HttpUtils.getHttpHeaders_validateOtp());
            String responseJson = httpUtils.getRestTemplate().postForObject(VALIDATE_OTP_URL, httpEntity, String.class);
            JSONObject responseJsonObject = new JSONObject(responseJson);
            String token = responseJsonObject.get("token").toString();
            boolean isNewAccount = "Y".equalsIgnoreCase(responseJsonObject.get("isNewAccount").toString());
            log.info("isNewAccount: {} and token: {}", isNewAccount, token);
            return token;
        }
        return null;
    }

    private String encodeHex(byte[] digest) {
        if (!ObjectUtils.isEmpty(digest)) {
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        return null;
    }

    private byte[] getSha256(String otp) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(otp.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
