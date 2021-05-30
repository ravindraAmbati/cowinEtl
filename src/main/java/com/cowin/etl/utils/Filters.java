package com.cowin.etl.utils;

import com.cowin.etl.constants.Functions;
import com.cowin.etl.model.Sessions;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.cowin.etl.utils.JsonConverter.convertToSessions;

@Component
@Slf4j
public class Filters {

    public static List<Sessions> defaultFilter(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Not Covaxin | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions.covaxinPredicate.negate())
                .filter(Functions._1DosePredicate)
                .filter(Functions._18Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _18(List<Sessions> sessionsList){
        log.info("defaultFilter -> 18+");
        return sessionsList
                .stream()
                .filter(Functions.anyDosePredicate)
                .filter(Functions._18Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _45(List<Sessions> sessionsList){
        log.info("defaultFilter -> 45+");
        return sessionsList
                .stream()
                .filter(Functions.anyDosePredicate)
                .filter(Functions._45Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _18_1Dose_covaxin_paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Covaxin | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions.covaxinPredicate)
                .filter(Functions._1DosePredicate)
                .filter(Functions._18Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _18_1Dose_covishield_paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Covishield | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions.covishieldPredicate)
                .filter(Functions._1DosePredicate)
                .filter(Functions._18Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _18_1Dose_Paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions._1DosePredicate)
                .filter(Functions._18Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _18_2Dose_Covishield_Paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Not Covaxin | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions.covishieldPredicate)
                .filter(Functions._2DosePredicate)
                .filter(Functions._18Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _18_2Dose_Covaxin_Paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Not Covaxin | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions.covaxinPredicate)
                .filter(Functions._2DosePredicate)
                .filter(Functions._18Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _45_1Dose_Paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Not Covaxin | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions._1DosePredicate)
                .filter(Functions._45Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _45_2Dose_Covishield_Paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Not Covaxin | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions.covishieldPredicate)
                .filter(Functions._2DosePredicate)
                .filter(Functions._45Predicate)
                .collect(Collectors.toList());
    }

    public static List<Sessions> _45_2Dose_Covaxin_Paid(List<Sessions> sessionsList){
        log.info("defaultFilter -> Paid | Not Covaxin | 1 Dose | 18+");
        return sessionsList
                .stream()
                .filter(Functions.paidPredicate)
                .filter(Functions.covaxinPredicate)
                .filter(Functions._2DosePredicate)
                .filter(Functions._45Predicate)
                .collect(Collectors.toList());
    }
}
