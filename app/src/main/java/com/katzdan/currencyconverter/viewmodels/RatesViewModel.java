package com.katzdan.currencyconverter.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.katzdan.currencyconverter.models.Rate;
import com.katzdan.currencyconverter.models.RatesData;

public class RatesViewModel extends AndroidViewModel {
    private MutableLiveData<RatesData> rateListObservable = null;

    public RatesViewModel(Application application, MutableLiveData<RatesData> rateListObservable) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        //rateListObservable = RatesRepository.getInstance().getRates();
        this.rateListObservable = rateListObservable;
    }

    public RatesViewModel(@NonNull Application application) {
        super(application);
        rateListObservable = null;
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public MutableLiveData<RatesData> getRatetListObservable() {
        return rateListObservable;
    }

    public void setBaseValue(float value){
        //return new
        RatesData ratesData = rateListObservable.getValue();

        //iterate through all currencies and update the calculated value
        for (String CurrencyCode :  ratesData.getRates().keySet()){
            Rate rate = ratesData.getRates().get(CurrencyCode);
            rate.setCalculatedValue(rate.getValue()*value);
            ratesData.getRates().put(CurrencyCode, rate);
        }

        rateListObservable.postValue(ratesData);
    }

}