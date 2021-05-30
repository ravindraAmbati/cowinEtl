package com.cowin.etl.api.avail;

import com.cowin.etl.api.meta.MetaService;
import com.cowin.etl.enums.DistrictEnum;
import com.cowin.etl.http.HttpUtils;
import com.cowin.etl.model.District;
import com.cowin.etl.model.Sessions;
import com.cowin.etl.model.State;
import com.cowin.etl.utils.Filters;
import com.cowin.etl.utils.JsonConverter;
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
import java.util.StringJoiner;

import static com.cowin.etl.constants.AppConstants.*;

@Service(value = "availService")
@Slf4j
public class AvailService {

    @Autowired
    private MetaService metaService;

    @Autowired
    private HttpUtils httpUtils;

    public List<Sessions> findByDate(String date) {

        log.info("find by date -> date: {}", date);
        List<Sessions> sessionsList = new ArrayList<>();
        final List<State> states = metaService.getStates();
        log.info("States: {}", states);
        states.forEach(
                state -> {
                    log.info("State Id: {} and Name: {}", state.getState_id(), state.getState_name());
                    sessionsList.addAll(findByState(state.getState_id(), date));
                }
        );
        return sessionsList;
    }

    public String findByDateJson(String date){
        return JsonConverter.toJson(findByDate(date));
    }

    public String findByDateAndFilterJson(String date){
        List<Sessions> sessionsList = findByDate(date);
        return JsonConverter.toJson(Filters._18(sessionsList));
    }


    public List<Sessions> findByState(int stateId, String date) {

        log.info("find by state: state id: {} and date: {}", stateId, date);
        List<Sessions> sessionsList = new ArrayList<>();
        List<District> districts = metaService.getDistricts(stateId);
        log.info("Districts: {}", districts);
        districts.forEach(
                district -> {
                    log.info("District Id: {} and Name: {}", district.getDistrict_id(), district.getDistrict_name());
                    sessionsList.addAll(findByDistrict(district.getDistrict_id(), date));
                }
        );
        return sessionsList;
    }

    public String findByStateJson(int stateId, String date){
        return JsonConverter.toJson(findByState(stateId, date));
    }

    public List<Sessions> findByDistrict(int districtId, String date) {
        log.info("find by district -> district id: {} and date: {}", districtId, date);
        ResponseEntity<String> responseEntity = httpUtils.getRestTemplate().exchange(FIND_BY_DISTRICT_ID_AND_DATE, HttpMethod.GET, new HttpEntity<>(BODY, HttpUtils.getHttpHeaders()), String.class, Map.of(DISTRICT_ID, String.valueOf(districtId), DATE, date));
        String responseBody = HttpUtils.getResponseBody(responseEntity);
        return JsonConverter.convertToSessions(responseBody);
    }

    private List<Sessions> findByDistricts(String date, DistrictEnum... districts) {
        List<Sessions> sessions = new ArrayList<>();
        for (DistrictEnum district : districts) {
            sessions.addAll(findByDistrict(district.getId(), date));
        }
        return sessions;
    }

    public String findByDistrictJson(int districtId, String date){
        return JsonConverter.toJson(findByDistrict(districtId, date));
    }

    public String findByDistrictsJson(String date, DistrictEnum... districts){
        return JsonConverter.toJson(findByDistricts(date, districts));
    }

    public String findByDistrictsAndFilterJson(String date, DistrictEnum... districts){
        List<Sessions> sessionsList = findByDistricts(date, districts);
        return JsonConverter.toJson(Filters._18(sessionsList));
    }
}
