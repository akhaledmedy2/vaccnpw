package com.digitalfactory.vaccnpw.dto;

public enum PaymentMethod {

    CASH("1"),
    CREDIT("2"),
    FAWRY("3");

    private final String key;

    PaymentMethod(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static PaymentMethod getEnum(String value) {
        for(PaymentMethod v : values())
            if(v.getKey().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }


}
