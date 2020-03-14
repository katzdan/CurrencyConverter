package com.katzdan.currencyconverter.models;

public class Rate {

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String code) {
        currencyCode = code;
    }

    public float getValue() { return value; }

    public void setValue(float value) { this.value = value; }

    public Float getCalculatedValue() { return calculatedValue; }

    public void setCalculatedValue(Float value) { this.calculatedValue = value; }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String code) {
        currencyName = code;
    }

    public Rate(String currencyCode, String currencyName, float baseValue, float calaulcatedValue){
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.value = baseValue;
        this.calculatedValue = calaulcatedValue;
    }
    private String currencyCode;
    private float value;
    private String currencyName;
    private float calculatedValue;

}
