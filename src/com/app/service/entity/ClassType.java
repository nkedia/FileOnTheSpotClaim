
package com.app.service.entity;

public enum ClassType{

    M_CYCLE("MCycle"),
    LMV("LMV"),
    HGV("HGV"),
    TRANSPORT("Transport"),
    NON_TRANSPORT("Non-Transport");

    private final String value;

    ClassType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClassType fromValue(String v) {
        for (ClassType c: ClassType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
