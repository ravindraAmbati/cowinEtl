package com.cowin.etl.api.avail;

import com.cowin.etl.api.meta.MetaService;
import com.cowin.etl.cache.MetaCache;
import com.cowin.etl.http.HttpUtils;
import com.cowin.etl.model.Center;
import com.cowin.etl.utils.Filters;
import com.cowin.etl.utils.JsonConverter;
import com.cowin.etl.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.cowin.etl.constants.AppConstants.*;

@Service(value = "availService")
@Slf4j
public class AvailService {

    @Autowired
    private MetaService metaService;

    @Autowired
    private HttpUtils httpUtils;

    public List<Center> searchAvail(Predicate<Center> predicate) {

        log.info("searchAvail -> ");
        final List<Center> centers = new ArrayList<>();
        final Set<Integer> stateIds = MetaCache.stateMap.keySet();
        log.info("searchAvail -> States Ids: {}", stateIds);
        stateIds.forEach(
                stateId -> {
                    log.info("searchAvail -> State Id: {} and Name: {}", stateId, MetaCache.stateMap.get(stateId));
                    centers.addAll(searchAvailByState(stateId, predicate));
                }
        );
        return centers;
    }

    public List<Center> searchAvailByState(int stateId, Predicate<Center> predicate) {

        log.info("searchAvailByState -> State Id: {} and Name: {}", stateId, MetaCache.stateMap.get(stateId));
        List<Center> centers = new ArrayList<>();
        List<Integer> districtIds = MetaCache.stateDistrictsMap.get(stateId);
        log.info("searchAvailByState -> Districts: {}", districtIds);
        return searchAvailByDistricts(predicate, districtIds);
    }


    public List<Center> searchAvailByDistricts(Predicate<Center> predicate, List<Integer> districtIds) {
        log.info("searchAvailByDistricts ->");
        List<Center> centers = new ArrayList<>();
        for (Integer districtId : districtIds) {
            centers.addAll(searchAvailByDistrict(districtId, predicate));
        }
        return centers;
    }

    public List<Center> searchAvailByDistrict(int districtId, Predicate<Center> predicate) {
        String todayDate = Utility.getTodayDate();
        log.info("searchAvailByDistrict -> date: {}", todayDate);
        ResponseEntity<String> responseEntity = httpUtils.getRestTemplate().exchange(CALENDAR_BY_DISTRICT_ID_AND_DATE, HttpMethod.GET, new HttpEntity<>(BODY, HttpUtils.getHttpHeaders()), String.class, Map.of(DISTRICT_ID, String.valueOf(districtId), DATE, todayDate));
        String responseBody = HttpUtils.getResponseBody(responseEntity);
        List<Center> centers = JsonConverter.convertToCenters(responseBody);
        if (!ObjectUtils.isEmpty(centers)) {
            if (!ObjectUtils.isEmpty(predicate)) {
                centers = centers.stream().filter(predicate).collect(Collectors.toList());
            } else {
                centers = centers.stream().filter(Filters.filters.get(0)).collect(Collectors.toList());
            }
        }
        return centers;
    }
}
