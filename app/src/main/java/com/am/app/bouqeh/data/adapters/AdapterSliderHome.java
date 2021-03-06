package com.am.app.bouqeh.data.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.am.app.bouqeh.utils.ImageHelper;
import com.bumptech.glide.Glide;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.ui.activities.main.MainActivity;
import com.am.app.bouqeh.ui.activities.product_details.ProductDetailsActivity;
import com.am.app.bouqeh.utils.RxBus;

import java.util.List;

public class AdapterSliderHome extends PagerAdapter {
    Context context;
    List<ModelProductItem> products;
    private LayoutInflater layoutInflater;

    public AdapterSliderHome(Context context, List<ModelProductItem> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slider_home_layout, container, false);
        ImageView im_slider = view.findViewById(R.id.im_slider);
        /*
        if (position == 0)
            Glide.with(context).load("https://i.imgur.com/oOtzK7F.jpg").asBitmap().into(im_slider);
        else if (position ==1)
            Glide.with(context).load("https://i.imgur.com/s9FLtEA.jpg").asBitmap().into(im_slider);
            */


       // Glide.with(context).load(products.get(position).getOfferImage()).asBitmap().into(im_slider);

        (new ImageHelper() {
            @Override
            protected void mOnException() {

            }

            @Override
            protected void mOnResourceReady() {

            }
        }).loadImage(context, im_slider,products.get(position).getOfferImage());

        //im_slider.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);

        im_slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("id", products.get(position).getId());
                context.startActivity(intent); */


                RxBus.publish(products.get(position));
                context.startActivity(new Intent(context, ProductDetailsActivity.class));
                ((MainActivity) context).overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return products.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}