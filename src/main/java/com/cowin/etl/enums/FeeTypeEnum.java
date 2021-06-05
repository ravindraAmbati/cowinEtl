package com.cowin.etl.enums;

public enum FeeTypeEnum {

    PAID(1, "PAID"),
    FREE(2, "FREE");

    private int id;
    private String name;

    FeeTypeEnum(int type, String name) {
        this.id = type;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
