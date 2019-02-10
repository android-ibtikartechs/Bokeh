package com.am.app.bouqeh.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import com.am.app.bouqeh.MvpApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public abstract class ImageHelper  {
    protected abstract void mOnException();
    protected abstract void mOnResourceReady();

    public void loadImage(Context context, ImageView imageView, String url)
    {
        context = checkContextNull(context);
        Glide.with(context)
                .load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        mOnException();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mOnResourceReady();
                        return false;
                    }
                })
                .thumbnail(0.01f)
                .into(imageView);
    }


    private Context checkContextNull(Context context)
    {
        if (context == null || context instanceof Activity && ((Activity) context).isDestroyed())
        {
            return MvpApp.getInstance().getApplicationContext();
        }
        else
            return context;
    }
}
