package com.katzdan.currencyconverter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.katzdan.currencyconverter.R;
import com.katzdan.currencyconverter.models.RatesData;
import com.katzdan.currencyconverter.ui.rates.OnListFragmentInteractionListener;


/**
 * {@link RecyclerView.Adapter} that can display text
 * TODO: Replace the implementation with code for your data type.
 */
public class RatesRecyclerViewAdapter extends RecyclerView.Adapter<RatesRecyclerViewAdapter.ViewHolder> {

    private final RatesData ratesData;
    private final OnListFragmentInteractionListener mListener;

    public RatesRecyclerViewAdapter(RatesData ratesData, OnListFragmentInteractionListener listener) {
        this.ratesData = ratesData;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.mItem = mValues.getRates().get(position);
        holder.mIdView.setText(ratesData.getRates().get(position).first);
        holder.mContentView.setText(ratesData.getRates().get(position).second.toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return ratesData.getRates().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
