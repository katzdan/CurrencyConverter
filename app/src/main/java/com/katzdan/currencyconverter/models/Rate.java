package com.katzdan.currencyconverter.models;

public class Rate {

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    private String Code;
    private float value;
}
