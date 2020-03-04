package com.katzdan.currencyconverter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.katzdan.currencyconverter.ui.rates.RatesFragment;

public class RatesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rates_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RatesFragment.newInstance())
                    .commitNow();
        }
    }
}
