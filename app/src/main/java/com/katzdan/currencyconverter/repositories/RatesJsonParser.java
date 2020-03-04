package com.katzdan.currencyconverter.repositories;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.katzdan.currencyconverter.Utils;
import com.katzdan.currencyconverter.models.RatesData;

import java.util.Map;

public class RatesJsonParser {

    private final static String TAG = "RatesJsonParser";
    public static RatesData parse(Context context, JsonElement jsonTree){

        RatesData ratesData = new RatesData();
        //ratesData.setDate(getCurrentDate());
        if(jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement date = jsonObject.get("date");
            ratesData.setDate(date.toString());
            JsonElement base = jsonObject.get("base");
            ratesData.setBase(base.getAsString());
            JsonElement ratesElement = jsonObject.get("rates");
            if (ratesElement.isJsonObject()){
                JsonObject rates = (JsonObject)ratesElement;
                for (Map.Entry entry : rates.entrySet()){
                    Log.i(TAG, "entry :" + entry.getKey() + ", value =" + entry.getValue());
                    String currencyCode = (String)entry.getKey();
                    String currencyName = Utils.getStringByIdName(context, currencyCode);
                    ratesData.getRates().put((String) entry.getKey(), new Pair<>(currencyName, Float.valueOf(entry.getValue().toString())));
                }
            }
        }
        return ratesData;
    }

}
