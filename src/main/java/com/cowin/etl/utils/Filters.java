package com.cowin.etl.utils;

import com.cowin.etl.constants.Functions;
import com.cowin.etl.enums.VaccineEnum;
import com.cowin.etl.model.Center;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.function.Predicate;

@Component
@Slf4j
public class Filters {

    public static HashMap<Integer, String> filterName = new HashMap<>();
    public static HashMap<Integer, Predicate<Center>> filters = new HashMap<>();

    static {

        filterName.put(0, "Dose: any(first or second)");
        filterName.put(1, "Dose: 1");
        filterName.put(2, "Dose: 2");
        filterName.put(3, "Fee: Paid");
        filterName.put(4, "Fee: Free");
        filterName.put(5, "Vaccine: " + VaccineEnum.COVAXIN.getName());
        filterName.put(6, "Vaccine: " + VaccineEnum.COVISHIELD.getName());
        filterName.put(7, "Vaccine: " + VaccineEnum.Sputnik_V.getName());
        filterName.put(8, "Age: 18+");
        filterName.put(9, "Age: 45+");

        filters.put(0, Functions.anyDosePredicate);
        filters.put(1, Functions._1DosePredicate);
        filters.put(2, Functions._2DosePredicate);
        filters.put(3, Functions.paidPredicate);
        filters.put(4, Functions.freePredicate);
        filters.put(5, Functions.covaxinPredicate);
        filters.put(6, Functions.covishieldPredicate);
        filters.put(7, Functions.sputnikVPredicate);
        filters.put(8, Functions._18Predicate);
        filters.put(9, Functions._45Predicate);

        filters.put(1358, Functions._1DosePredicate.and(Functions.paidPredicate).and(Functions.covaxinPredicate).and(Functions._18Predicate));
        filters.put(2358, Functions._2DosePredicate.and(Functions.paidPredicate).and(Functions.covaxinPredicate).and(Functions._18Predicate));

        filters.put(1458, Functions._1DosePredicate.and(Functions.freePredicate).and(Functions.covaxinPredicate).and(Functions._18Predicate));
        filters.put(2458, Functions._2DosePredicate.and(Functions.freePredicate).and(Functions.covaxinPredicate).and(Functions._18Predicate));

        filters.put(1368, Functions._1DosePredicate.and(Functions.paidPredicate).and(Functions.covishieldPredicate).and(Functions._18Predicate));
        filters.put(2368, Functions._2DosePredicate.and(Functions.paidPredicate).and(Functions.covishieldPredicate).and(Functions._18Predicate));

        filters.put(1468, Functions._1DosePredicate.and(Functions.freePredicate).and(Functions.covishieldPredicate).and(Functions._18Predicate));
        filters.put(2468, Functions._2DosePredicate.and(Functions.freePredicate).and(Functions.covishieldPredicate).and(Functions._18Predicate));

        filters.put(1378, Functions._1DosePredicate.and(Functions.paidPredicate).and(Functions.sputnikVPredicate).and(Functions._18Predicate));
        filters.put(2378, Functions._2DosePredicate.and(Functions.paidPredicate).and(Functions.sputnikVPredicate).and(Functions._18Predicate));

        filters.put(1478, Functions._1DosePredicate.and(Functions.freePredicate).and(Functions.sputnikVPredicate).and(Functions._45Predicate));
        filters.put(2478, Functions._2DosePredicate.and(Functions.freePredicate).and(Functions.sputnikVPredicate).and(Functions._45Predicate));

        filters.put(1359, Functions._1DosePredicate.and(Functions.paidPredicate).and(Functions.covaxinPredicate).and(Functions._45Predicate));
        filters.put(2359, Functions._2DosePredicate.and(Functions.paidPredicate).and(Functions.covaxinPredicate).and(Functions._45Predicate));

        filters.put(1459, Functions._1DosePredicate.and(Functions.freePredicate).and(Functions.covaxinPredicate).and(Functions._45Predicate));
        filters.put(2459, Functions._2DosePredicate.and(Functions.freePredicate).and(Functions.covaxinPredicate).and(Functions._45Predicate));

        filters.put(1369, Functions._1DosePredicate.and(Functions.paidPredicate).and(Functions.covishieldPredicate).and(Functions._45Predicate));
        filters.put(2369, Functions._2DosePredicate.and(Functions.paidPredicate).and(Functions.covishieldPredicate).and(Functions._45Predicate));

        filters.put(1469, Functions._1DosePredicate.and(Functions.freePredicate).and(Functions.covishieldPredicate).and(Functions._45Predicate));
        filters.put(2469, Functions._2DosePredicate.and(Functions.freePredicate).and(Functions.covishieldPredicate).and(Functions._45Predicate));

        filters.put(1379, Functions._1DosePredicate.and(Functions.paidPredicate).and(Functions.sputnikVPredicate).and(Functions._45Predicate));
        filters.put(2379, Functions._2DosePredicate.and(Functions.paidPredicate).and(Functions.sputnikVPredicate).and(Functions._45Predicate));

        filters.put(1479, Functions._1DosePredicate.and(Functions.freePredicate).and(Functions.sputnikVPredicate).and(Functions._45Predicate));
        filters.put(2479, Functions._2DosePredicate.and(Functions.freePredicate).and(Functions.sputnikVPredicate).and(Functions._45Predicate));

    }
}
