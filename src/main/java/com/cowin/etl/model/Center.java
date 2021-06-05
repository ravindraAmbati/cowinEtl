package com.cowin.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Center {

    private long center_id;
    private String name;
    private String address;
    private String state_name;
    private String district_name;
    private String block_name;
    private long pincode;
    private float lat;
    private float Long;
    private LocalTime from;
    private LocalTime to;
    private String fee_type;
    private Sessions[] sessions;
    private Vaccine_Fees[] vaccine_fees;
}
