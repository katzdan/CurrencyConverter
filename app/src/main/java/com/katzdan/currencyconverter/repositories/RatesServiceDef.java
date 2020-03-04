package com.katzdan.currencyconverter.repositories;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RatesServiceDef {

    @GET(".")
    Call<JsonObject> getRates();

}
