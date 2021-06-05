package com.cowin.etl.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Component
public class Utility {

    @NotNull
    public static String getTodayDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy").toFormatter();
        return now.format(dateTimeFormatter);
    }
}
