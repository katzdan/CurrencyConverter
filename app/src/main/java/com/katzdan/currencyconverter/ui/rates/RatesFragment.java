package com.katzdan.currencyconverter.ui.rates;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.katzdan.currencyconverter.R;
import com.katzdan.currencyconverter.adapters.RatesRecyclerViewAdapter;
import com.katzdan.currencyconverter.models.Rate;
import com.katzdan.currencyconverter.models.RatesData;
import com.katzdan.currencyconverter.repositories.Repository;
import com.katzdan.currencyconverter.viewmodels.RatesViewModel;

import java.util.ArrayList;
import java.util.List;

public class RatesFragment extends Fragment implements OnListFragmentInteractionListener {

    private static final String TAG = "RatesFragment";
    private RatesRecyclerViewAdapter ratesRecyclerViewAdapter;
    private RatesViewModel mViewModel;
    private RecyclerView mRecyclerView;

    public static RatesFragment newInstance() {
        return new RatesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void instantiateRecyclerView(MutableLiveData<RatesData> ratesData) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RatesFragment.this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(ratesRecyclerViewAdapter);
    }

    private void instantiateViewModel(MutableLiveData<RatesData> ratesData) {
        mViewModel = new RatesViewModel(getActivity().getApplication(), ratesData);
        mViewModel.getRatetListObservable().observe(this, new Observer<RatesData>() {
            @Override
            public void onChanged(RatesData ratesData) {
                Log.i(TAG, "onChanged triggered");
                ratesRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rates_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        Repository repository = new Repository();

        repository.start(this.getActivity(), new Repository.CallbackFunc() {
            @Override
            public void callback(MutableLiveData<RatesData> ratesData) {
                ratesRecyclerViewAdapter = new RatesRecyclerViewAdapter(ratesData.getValue(), RatesFragment.this);
                instantiateViewModel(ratesData);
                fillAddCurrencySpinner(ratesData);
                instantiateRecyclerView(ratesData);
                setListeners();
            }
        });

        return view;
    }

    private void setListeners() {
        EditText editTextValue = getActivity().findViewById(R.id.editTextValue);
        if (editTextValue!=null){
            editTextValue.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    Log.i(TAG, "onKey value  = " + editTextValue.getText().toString());
                    if (editTextValue.getText().toString()==null || editTextValue.getText().toString().equals(""))
                    {
                        return false;
                    }

                    Float newValue = Float.parseFloat(editTextValue.getText().toString());
                    mViewModel.setBaseValue(newValue);
                    return false;
                }
            });
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RatesViewModel.class);
        // TODO: Use the ViewModel

    }

    private void fillAddCurrencySpinner(MutableLiveData<RatesData> ratesData) {

        List<String> spinnerArray = new ArrayList<>();

        ratesData.getValue().getRates().values().stream().forEach(rate -> spinnerArray.add(rate.getCurrencyName()));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = this.getActivity().findViewById(R.id.spinnerAddCurrency);
        spinner.setAdapter(adapter);

        ((BaseAdapter) spinner.getAdapter()).notifyDataSetChanged();
        spinner.invalidate();
        spinner.setSelection(0);
    }

    @Override
    public void onListFragmentInteraction(Rate rate) {
        Log.i(TAG, "onListFragmentInteraction started, rate = " + rate.toString());
    }


}
