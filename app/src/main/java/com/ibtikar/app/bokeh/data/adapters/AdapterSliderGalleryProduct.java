package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.GalleryProductImage;

import java.util.List;

public class AdapterSliderGalleryProduct extends PagerAdapter {
    Context context;
    List<GalleryProductImage> products;
    private LayoutInflater layoutInflater;

    public AdapterSliderGalleryProduct(Context context, List<GalleryProductImage> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slider_gallery_product, container, false);
        ImageView im_slider = view.findViewById(R.id.im_slider);
        Glide.with(context).load(products.get(position).getImage()).asBitmap().into(im_slider);
        //im_slider.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(view);

        im_slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("id", products.get(position).getId());
                context.startActivity(intent); */
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