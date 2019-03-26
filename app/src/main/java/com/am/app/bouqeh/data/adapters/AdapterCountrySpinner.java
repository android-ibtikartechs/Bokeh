package com.am.app.bouqeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.app.bouqeh.utils.ImageHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.models.ModelCountry;

import java.util.List;

public class AdapterCountrySpinner extends ArrayAdapter<ModelCountry> {
    private final Context mContext;
    private final List<ModelCountry> items;

    public AdapterCountrySpinner(@NonNull Context context, int resource,  @NonNull List objects) {
        super(context, resource, objects);
        mContext = context;
        items = objects;
    }

    @Override
    public void setDropDownViewResource(int resource) {
        super.setDropDownViewResource(resource);
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
        final View view = inflater.inflate(R.layout.view_country_spinner, parent, false);
        TextView tvCity = view.findViewById(R.id.tv_title_country);
        ImageView imFlag = view.findViewById(R.id.im_flag);

        ModelCountry country = items.get(position);

        if (!(country.getImage().equals("") || country.getImage() == null ))
        {

            (new ImageHelper() {
                @Override
                protected void mOnException() {

                }

                @Override
                protected void mOnResourceReady() {

                }
            }).loadImage(mContext,imFlag,country.getImage());
        }
        tvCity.setText(country.getTitle());


        return view;
    }

}
