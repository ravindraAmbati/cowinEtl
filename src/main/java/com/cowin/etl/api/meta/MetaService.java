package com.cowin.etl.api.meta;

import com.cowin.etl.enums.StateEnum;
import com.cowin.etl.http.HttpUtils;
import com.cowin.etl.model.District;
import com.cowin.etl.model.State;
import com.cowin.etl.utils.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.cowin.etl.constants.AppConstants.*;

@Service(value = "metaService")
@Slf4j
public class MetaService {

    @Autowired
    private HttpUtils httpUtils;

    public String getStatesJson() {
        log.info("get states json -> ");
        ResponseEntity<String> responseEntity = httpUtils.getRestTemplate().exchange(METADATA_STATES_URL, HttpMethod.GET, new HttpEntity<>(BODY, HttpUtils.getHttpHeaders()), String.class);
        return HttpUtils.getResponseBody(responseEntity);
    }

    public List<State> getStates() {
        log.info("get states -> ");
        return JsonConverter.convertToStates(getStatesJson());
    }

    public String getDistrictsJson(int stateId) {
        log.info("get districts json -> stateId: {}", stateId);
        ResponseEntity<String> responseEntity = httpUtils.getRestTemplate().exchange(METADATA_DISTRICTS_URL, HttpMethod.GET, new HttpEntity<>(BODY, HttpUtils.getHttpHeaders()), String.class, Collections.singletonMap(STATE_ID, String.valueOf(stateId)));
        return HttpUtils.getResponseBody(responseEntity);
    }

    public List<District> getDistricts(int stateId) {
        log.info("get districts -> stateId: {}", stateId);
        return JsonConverter.convertToDistricts(getDistrictsJson(stateId));
    }

    public List<District> getTsDistricts() {
        log.info("get TS districts -> ");
        return getDistricts(StateEnum.TS.getId());
    }

    public List<District> getApDistricts() {
        log.info("get AP districts -> ");
        return getDistricts(StateEnum.AP.getId());
    }

    public String getTsDistrictsJson() {
        log.info("get TS districts json-> ");
        return getDistrictsJson(StateEnum.TS.getId());
    }

    public String getApDistrictsJson() {
        log.info("get AP districts json-> ");
        return getDistrictsJson(StateEnum.AP.getId());
    }

}
