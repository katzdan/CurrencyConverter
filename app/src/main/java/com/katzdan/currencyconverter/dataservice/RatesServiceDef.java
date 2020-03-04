package com.katzdan.currencyconverter.dataservice;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RatesServiceDef {


    // example url : https://api.exchangeratesapi.io/latest?base=USD


    //@Path("user") String user
    @GET(".")
    Call<JsonObject> getRates();

    //@GET("/repos/{user}/{reponame}")
    //Call<Rates> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);
}
