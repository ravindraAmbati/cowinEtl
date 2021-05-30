package com.cowin.etl.constants;

import com.cowin.etl.model.Sessions;
import org.springframework.http.HttpStatus;

import java.util.function.Predicate;

public class Functions {

    public static final Predicate<HttpStatus> httpStatusOkPredicate = HttpStatus.OK::equals;

    public static final Predicate<Sessions> anyDosePredicate = s -> 0 < s.getAvailable_capacity();

    public static final Predicate<Sessions> paidPredicate = s -> AppConstants.PAID.equalsIgnoreCase(s.getFee_type());
    public static final Predicate<Sessions> freePredicate = s -> AppConstants.FREE.equalsIgnoreCase(s.getFee_type());

    public static final Predicate<Sessions> _1DosePredicate = s -> 0 < s.getAvailable_capacity_dose1();
    public static final Predicate<Sessions> _2DosePredicate = s -> 0 < s.getAvailable_capacity_dose2();

    public static final Predicate<Sessions> _18Predicate = s -> 18 == s.getMin_age_limit();
    public static final Predicate<Sessions> _45Predicate = s -> 45 == s.getMin_age_limit();

    public static final Predicate<Sessions> covishieldPredicate = s -> AppConstants.COVISHIELD.equalsIgnoreCase(s.getVaccine());
    public static final Predicate<Sessions> covaxinPredicate = s -> AppConstants.COVAXIN.equalsIgnoreCase(s.getVaccine());
    public static final Predicate<Sessions> sputnikPredicate = s -> AppConstants.Sputnik_V.equalsIgnoreCase(s.getVaccine());

}
