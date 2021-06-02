package com.cowin.etl.constants;

public class AppConstants {

    public static final String METADATA_STATES_URL = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
    public static final String METADATA_DISTRICTS_URL = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/{state_id}";
    public static final String FIND_BY_DISTRICT_ID_AND_DATE = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByDistrict?district_id={district_id}&date={date}";

    public static final String STATE_ID = "state_id";
    public static final String DISTRICT_ID = "district_id";
    public static final String DATE = "date";
    public static final String BODY = "httpBody";
    public static final String FAILED = "FAILED";

    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String EN_US = "en_US";
    public static final String USER_AGENT = "user-agent";
    public static final String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
    public static final String USER_AGENT_VALUE_2 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36";


    public static final String EMPTY = "";
    public static final String PAID = "Paid";
    public static final String FREE = "Free";

    public static final String COVISHIELD = "COVISHIELD";
    public static final String COVAXIN = "COVAXIN";
    public static final String Sputnik_V = "Sputnik V";
}
