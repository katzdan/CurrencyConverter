package com.katzdan.currencyconverter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.json.JSONObject;


public class RatesListViewModel extends AndroidViewModel {
    private final LiveData<JSONObject> rateListObservable;

    public RatesListViewModel(Application application, LiveData<JSONObject> rateListObservable) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        //rateListObservable = RatesRepository.getInstance().getRates();
        this.rateListObservable = rateListObservable;
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<JSONObject> getRatetListObservable() {
        return rateListObservable;
    }
}