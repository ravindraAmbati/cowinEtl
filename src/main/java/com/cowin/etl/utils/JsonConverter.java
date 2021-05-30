package com.cowin.etl.utils;

import com.cowin.etl.model.District;
import com.cowin.etl.model.Sessions;
import com.cowin.etl.model.State;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static com.cowin.etl.constants.Functions.*;

@Component
@Slf4j
public class JsonConverter {

    public static final Gson gson = new Gson();
    private static JSONObject jsonObject;

    public StringJoiner getJoiner() {
        return new StringJoiner(",", "{", "}");
    }

    public static List<Sessions> convertToSessions(String json) {
        log.info("convert to sessions -> json: {}", json);
        jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("sessions");
        log.info("convert to sessions -> jsonArray: {}", jsonArray);
        Sessions[] sessions = gson.fromJson(jsonArray.toString(), Sessions[].class);
        log.info("convert to sessions -> sessions: {}", Arrays.toString(sessions));
        return Arrays.stream(sessions)
                .filter(anyDosePredicate)
                .filter(paidPredicate)
                .filter(_18Predicate)
                .collect(Collectors.toList());
    }

    public static List<State> convertToStates(String json) {
        log.info("convert to states -> json: {}", json);
        jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("states");
        log.info("convert to states -> jsonArray: {}", jsonArray);
        State[] states = gson.fromJson(jsonArray.toString(), State[].class);
        log.info("convert to states -> states: {}", Arrays.toString(states));
        return List.of(states);
    }

    public static List<District> convertToDistricts(String json) {
        log.info("convert to districts -> json: {}", json);
        jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("districts");
        log.info("convert to districts -> jsonArray: {}", jsonArray);
        District[] districts = gson.fromJson(jsonArray.toString(), District[].class);
        log.info("convert to districts -> districts: {}", Arrays.toString(districts));
        return List.of(districts);
    }


    public static String toJson(List<Sessions> sessionsList) {
        if (!ObjectUtils.isEmpty(sessionsList)) {
            return gson.toJson(sessionsList);
        }
        return null;
    }
}
