package com.cowin.etl.extract;

import com.cowin.etl.http.HttpUtils;
import com.cowin.etl.model.District;
import com.cowin.etl.model.Sessions;
import com.cowin.etl.model.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cowin.etl.utils.JsonConverter.*;

@Slf4j
@RestController
@RequestMapping("/extract")
public class ExtractController {

    @Autowired
    RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByDate(@PathVariable String date) {
        List<Sessions> filteredSessions = new ArrayList<>();
        State[] statesArray = convertToStates(getStates());
        for (State state : statesArray) {
            String stateId = String.valueOf(state.getState_id());
            String districtJson = getDistricts(stateId);
            District[] districtArray = convertToDistricts(districtJson);
            for (District district : districtArray) {
                String sessionsJson = findByDistrict(String.valueOf(district.getDistrict_id()), date);
                filteredSessions.addAll(filterSessions(sessionsJson));
            }
        }
        return filteredSessions.toString();
    }

    @GetMapping(value = "/state/{state_id}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByState(@PathVariable String state_id, @PathVariable String date) {
        String districtJson = getDistricts(state_id);
        District[] districtArray = convertToDistricts(districtJson);
        List<Sessions> filteredSessions = new ArrayList<>();
        for (District district : districtArray) {
            String sessionsJson = findByDistrict(String.valueOf(district.getDistrict_id()), date);
            filteredSessions.addAll(filterSessions(sessionsJson));
        }
        return filteredSessions.toString();
    }

    @GetMapping(value = "/states", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getStates() {
        String url = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
        HttpEntity<String> request = new HttpEntity<>("body", HttpUtils.getHttpHeaders());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        Assert.isTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()), "failed");
        return responseEntity.getBody();
    }

    @GetMapping(value = "/districts/ts", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getTsDistricts() {
        return getDistricts("32");
    }

    @GetMapping(value = "/districts/ap", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getApDistricts() {
        return getDistricts("2");
    }

    @GetMapping(value = "/districts/{state_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getDistricts(@PathVariable String state_id) {
        String url = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/" + state_id;
        HttpEntity<String> request = new HttpEntity<>("body", HttpUtils.getHttpHeaders());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        Assert.isTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()), "failed");
        return responseEntity.getBody();
    }

    @GetMapping(value = "/findByDistrict/{district_id}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByDistrict(@PathVariable String district_id, @PathVariable String date) {
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id={district_id}&date={date}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("district_id", district_id);
        uriVariables.put("date", date);
        HttpEntity<String> request = new HttpEntity<>("body", HttpUtils.getHttpHeaders());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class, uriVariables);
        Assert.isTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()), "failed");
        return responseEntity.getBody();
    }

    @GetMapping(value = "/findByDistrict/hyd/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInHyd(@PathVariable String date) {
        String jsonResponse = findByDistrict("581", date);
        List<Sessions> sessions = filterSessions(jsonResponse);
        return sessions.toString();
    }

    @NotNull
    private List<Sessions> filterSessions(String response) {
        List<Sessions> sessions = List.of(convertToSessions(response));
        sessions = sessions
                .stream()
                .filter(c -> "Paid".equals(c.getFee_type()))
                .filter(c -> Integer.parseInt(c.getAvailable_capacity_dose1()) > 0)
                .filter(c -> Integer.parseInt(c.getMin_age_limit()) == 18)
                .collect(Collectors.toList());
        return sessions;
    }

    @GetMapping(value = "/findByDistrict/rnr/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findInRnr(@PathVariable String date) {
        return findByDistrict("603", date);
    }

    @GetMapping(value = "/findByDistrict/sgr/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findBySgr(@PathVariable String date) {
        return findByDistrict("604", date);
    }

    @GetMapping(value = "/findByDistrict/Mdc/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String findByMdc(@PathVariable String date) {
        return findByDistrict("596", date);
    }
}
