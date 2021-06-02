package com.cowin.etl.http;

import com.cowin.etl.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.cowin.etl.constants.AppConstants.FAILED;
import static com.cowin.etl.constants.Functions.httpStatusOkPredicate;

@Component
@Slf4j
public class HttpUtils {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

    @Autowired
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(AppConstants.ACCEPT_LANGUAGE, AppConstants.EN_US);
        headers.set(AppConstants.USER_AGENT, AppConstants.USER_AGENT_VALUE);
        return headers;
    }

    public static HttpHeaders getHttpHeaders_validateOtp() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AppConstants.USER_AGENT, AppConstants.USER_AGENT_VALUE_2);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("origin", "https://selfregistration.cowin.gov.in");
        headers.set("sec-fetch-site", "cross-site");
        headers.set("sec-fetch-mode", "cors");
        headers.set("sec-fetch-dest", "empty");
        headers.set("referer", "https://selfregistration.cowin.gov.in/");
        return headers;
    }

    public static String getResponseBody(ResponseEntity<String> responseEntity) {
        log.info("get response body -> http status code: {}", responseEntity.getStatusCode());
        return httpStatusOkPredicate.test(responseEntity.getStatusCode()) ? responseEntity.getBody() : FAILED;
    }
}
