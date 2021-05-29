package com.cowin.etl.utils;

import com.cowin.etl.model.District;
import com.cowin.etl.model.Sessions;
import com.cowin.etl.model.State;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonConverter {

    public static Sessions[] convertToSessions(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("sessions");
        Gson gson = new Gson();
        return gson.fromJson(jsonArray.toString(), Sessions[].class);
    }

    public static District[] convertToDistricts(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("districts");
        Gson gson = new Gson();
        return gson.fromJson(jsonArray.toString(), District[].class);
    }

    public static State[] convertToStates(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("states");
        Gson gson = new Gson();
        return gson.fromJson(jsonArray.toString(), State[].class);
    }

}
