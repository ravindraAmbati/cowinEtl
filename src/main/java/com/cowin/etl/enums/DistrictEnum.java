package com.cowin.etl.enums;

public enum DistrictEnum {

    HYD(581, "Hyderabad"),
    RND(603, "Rangareddy"),
    SGR(604, "Sangareddy"),
    MDL(596, "Medchal"),
    KRL(7, "Kurnool"),
    VSP(8, "Visakhapatnam"),
    WGD(16, "West Godavari");

    private int id;
    private String name;

    DistrictEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
