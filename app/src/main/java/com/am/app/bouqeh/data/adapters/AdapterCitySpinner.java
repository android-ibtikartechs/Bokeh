package com.am.app.bouqeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.am.app.bouqeh.data.models.ModelCity;

import java.util.List;

public class AdapterCitySpinner extends ArrayAdapter<ModelCity> {

    private final Context mContext;
    private final List<ModelCity> items;

    public AdapterCitySpinner(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        TextView tvCity = (TextView) view.findViewById(android.R.id.text1);

        ModelCity city = items.get(position);

        tvCity.setText(city.getName());

        return view;
    }

}
