package com.cowin.etl.constants;

import com.cowin.etl.enums.FeeTypeEnum;
import com.cowin.etl.enums.VaccineEnum;
import com.cowin.etl.model.Center;
import com.cowin.etl.model.Sessions;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

import java.util.function.Predicate;

public class Functions {

    public static final Predicate<HttpStatus> httpStatusOkPredicate = HttpStatus.OK::equals;

    public static final Predicate<Center> paidPredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            return FeeTypeEnum.PAID.getName().equalsIgnoreCase(center.getFee_type());
        }
        return false;
    };

    public static final Predicate<Center> freePredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            return FeeTypeEnum.FREE.getName().equalsIgnoreCase(center.getFee_type());
        }
        return false;
    };

    public static final Predicate<Center> anyDosePredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (0 < s.getAvailable_capacity()) {
                    return true;
                }
            }
        }
        return false;
    };

    public static final Predicate<Center> _1DosePredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (0 < s.getAvailable_capacity_dose1()) {
                    return true;
                }
            }
        }
        return false;
    };

    public static final Predicate<Center> _2DosePredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (0 < s.getAvailable_capacity_dose2()) {
                    return true;
                }
            }
        }
        return false;
    };

    public static final Predicate<Center> _18Predicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (18 == s.getMin_age_limit()) {
                    return true;
                }
            }
        }
        return false;
    };

    public static final Predicate<Center> _45Predicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (45 == s.getMin_age_limit()) {
                    return true;
                }
            }
        }
        return false;
    };

    public static final Predicate<Center> covishieldPredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (VaccineEnum.COVISHIELD.getName().equalsIgnoreCase(s.getVaccine())) {
                    return true;
                }
            }
        }
        return false;
    };

    public static final Predicate<Center> covaxinPredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (VaccineEnum.COVAXIN.getName().equalsIgnoreCase(s.getVaccine())) {
                    return true;
                }
            }
        }
        return false;
    };

    public static final Predicate<Center> sputnikVPredicate = (center) -> {
        if (!ObjectUtils.isEmpty(center)) {
            Sessions[] sessions = center.getSessions();
            for (Sessions s : sessions) {
                if (VaccineEnum.Sputnik_V.getName().equalsIgnoreCase(s.getVaccine())) {
                    return true;
                }
            }
        }
        return false;
    };
}
