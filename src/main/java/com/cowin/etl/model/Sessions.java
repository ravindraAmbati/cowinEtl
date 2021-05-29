package com.cowin.etl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sessions implements Serializable {

    private String center_id;
    private String name;
    private String address;
    private String state_name;
    private String district_name;
    private String block_name;
    private String pincode;
    private String from;
    private String to;
    private String lat;
//    private String log;
    private String fee_type;
    private String session_id;
    private String date;
    private String available_capacity_dose1;
    private String available_capacity_dose2;
    private String available_capacity;
    private String fee;
    private String min_age_limit;
    private String vaccine;
    private List<String> slots;

}