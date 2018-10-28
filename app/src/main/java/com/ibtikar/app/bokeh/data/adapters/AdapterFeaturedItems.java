package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
                    .into(itemViewHolder.imItem);
        }

        Toast.makeText(context, modelProductItem.getName(), Toast.LENGTH_SHORT).show();

        itemViewHolder.loutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customListener.onItemClickListener(modelProductItem.getId(),modelProductItem.getName(),modelProductItem.getImage(),modelProductItem.getPrice(),modelProductItem.getIsSameDayDelivery(),modelProductItem.getSellername(), modelProductItem.getIsLiked(), modelProductItem.getDetails());
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

        @BindView(R.id.lout_cont)
        ConstraintLayout loutContainer;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ContainerFeaturedItemsClickListener {
        public void onItemClickListener(Integer id, String title, String imUrl, Integer price, boolean isSameDayDelivery, String sellerName, boolean isLiked, String description);
    }
    public void setCustomButtonListner(ContainerFeaturedItemsClickListener listener) {
        this.customListener = listener;
    }
}
