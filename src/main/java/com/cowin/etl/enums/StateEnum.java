package com.cowin.etl.enums;

public enum StateEnum {


    TS(32, "Telangana"), AP(2, "Andhra Pradesh");

    private int id;
    private String name;

    StateEnum(int id, String name) {
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
