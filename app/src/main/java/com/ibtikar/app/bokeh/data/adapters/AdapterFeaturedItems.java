package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterFeaturedItems extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ModelProductItem> arrayList;
    private Context context;
    private ContainerFeaturedItemsClickListener customListener;

    public AdapterFeaturedItems(ArrayList<ModelProductItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.view_featured_item, parent, false);
        viewHolder = new ItemViewHolder(viewItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ModelProductItem modelProductItem = arrayList.get(position);
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        if (!(modelProductItem.getImage().equals("") || modelProductItem.getImage() == null ))
        {
            Glide.with(context)
                    .load(modelProductItem.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            itemViewHolder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(itemViewHolder.imItem);
        }

        Toast.makeText(context, modelProductItem.getName(), Toast.LENGTH_SHORT).show();

        itemViewHolder.loutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customListener.onFeaturedItemClickListener(modelProductItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void add(ModelProductItem r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(List<ModelProductItem> opResults) {
        for (ModelProductItem result : opResults) {
            add(result);
        }
    }



    protected class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView)
        ImageView imItem;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        @BindView(R.id.lout_cont)
        ConstraintLayout loutContainer;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            if (progressBar != null) {
                progressBar.setIndeterminate(true);
                progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.ColorGrayLine), android.graphics.PorterDuff.Mode.MULTIPLY);
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }

    public interface ContainerFeaturedItemsClickListener {
        public void onFeaturedItemClickListener(ModelProductItem productItem);
    }
    public void setCustomButtonListner(ContainerFeaturedItemsClickListener listener) {
        this.customListener = listener;
    }
}
