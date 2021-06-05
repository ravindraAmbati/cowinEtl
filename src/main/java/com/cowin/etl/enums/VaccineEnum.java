package com.cowin.etl.enums;

public enum VaccineEnum {

    COVISHIELD(1, "COVISHIELD"), COVAXIN(2, "COVAXIN"), Sputnik_V(3, "Sputnik V");

    private int id;
    private String name;

    VaccineEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
