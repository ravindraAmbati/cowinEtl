package com.cowin.etl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sessions {

    private String session_id;
    private LocalDate date;
    private int available_capacity;
    private int min_age_limit;
    private String vaccine;
    private String[] slots;
    private int available_capacity_dose1;
    private int available_capacity_dose2;
}