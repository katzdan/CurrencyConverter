package com.katzdan.currencyconverter;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.katzdan.currencyconverter.dataservice.RatesData;
import com.katzdan.currencyconverter.dataservice.RatesServiceDef;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataFetcher implements Callback<JsonObject> {

    private static final String TAG = "Controller";
    String HTTPS_API_URL = "https://api.exchangeratesapi.io/latest?base=USD";
    CallbackFunc callbackFunc;
    Context context;

    public void start(Context context, CallbackFunc callbackFunc) {

        this.context = context;

        this.callbackFunc = callbackFunc;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTPS_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RatesServiceDef ratesServiceDef = retrofit.create(RatesServiceDef.class);

        Call<JsonObject> call = ratesServiceDef.getRates();
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        if(response.isSuccessful()) {

            Log.i(TAG, response.body().toString());

            JsonParser parser = new JsonParser();

            JsonElement jsonTree = parser.parse(response.body().toString());
            RatesData ratesData = RatesJsonParser.parse(context, jsonTree);

            Log.i(TAG, "Rates data = " + ratesData.toString());

            this.callbackFunc.callback(ratesData);

        } else {
            Log.e(TAG, response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        t.printStackTrace();
    }

    public interface CallbackFunc {
        void callback(RatesData ratesData);
    }
}
