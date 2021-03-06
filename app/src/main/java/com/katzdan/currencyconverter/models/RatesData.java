package com.katzdan.currencyconverter.models;

import android.util.Pair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class RatesData {

    @SerializedName("rates")
    @Expose
    private HashMap<String, Rate> rates = new HashMap<>();
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;

    public HashMap<String, Rate> getRates() {
        return rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return new StringBuilder().append("Date:").append(this.date).append("\n")
                .append("Base:").append(this.base).append("\n")
                .append("rates:").append(rates.toString()).toString();
    }
}
