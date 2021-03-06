package com.am.app.bouqeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.app.bouqeh.utils.ImageHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.models.ModelShopItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterShopsList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ModelShopItem> arrayList;
    private Context context;
    private ContainerClickListener customListener;
    private boolean isFullList;


    public AdapterShopsList(List<ModelShopItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public AdapterShopsList(List<ModelShopItem> arrayList, Context context, boolean isFullList) {
        this.arrayList = arrayList;
        this.context = context;
        this.isFullList = isFullList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem;
        if (isFullList) {
            viewItem = inflater.inflate(R.layout.view_shop_list, parent, false);
            viewHolder = new LinearListItemViewHolder(viewItem);
        }
        else {
            viewItem = inflater.inflate(R.layout.view_featured_shops_item, parent, false);
            viewHolder = new ItemViewHolder(viewItem);
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isFullList)
        {
            final LinearListItemViewHolder itemViewHolder;
            final ModelShopItem shopsListItem = arrayList.get(position);
            itemViewHolder = (LinearListItemViewHolder) holder;
            if (!(shopsListItem.getImage().equals("") || shopsListItem.getImage() == null)) {
               /* Glide.with(context)
                        .load(shopsListItem.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemViewHolder.imShop);*/

                (new ImageHelper() {
                    @Override
                    protected void mOnException() {

                    }

                    @Override
                    protected void mOnResourceReady() {
                    }
                }).loadImage(context, itemViewHolder.imShop,shopsListItem.getImage());


            }

            itemViewHolder.tvTitle.setText(shopsListItem.getName());
            String address = shopsListItem.getCity() + ", " + shopsListItem.getArea();
            itemViewHolder.tvAddress.setText(address);
            itemViewHolder.loutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customListener.onItemShopClickListener(shopsListItem.getId(), shopsListItem.getName());
                }
            });

        }

        else {
            final ItemViewHolder itemViewHolder;
            final ModelShopItem shopsListItem = arrayList.get(position);
            itemViewHolder = (ItemViewHolder) holder;
            if (!(shopsListItem.getImage().equals("") || shopsListItem.getImage() == null)) {
                Glide.with(context)
                        .load(shopsListItem.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemViewHolder.imShop);
            }

            itemViewHolder.tvTitle.setText(shopsListItem.getName());
            itemViewHolder.loutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customListener.onItemShopClickListener(shopsListItem.getId(), shopsListItem.getName());
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void add(ModelShopItem r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(List<ModelShopItem> opResults) {
        for (ModelShopItem result : opResults) {
            add(result);
        }
    }


    protected class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView)
        ImageView imShop;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.lout_cont)
        ConstraintLayout loutContainer;



        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    protected class LinearListItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView)
        ImageView imShop;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.lout_cont)
        ConstraintLayout loutContainer;

        @BindView(R.id.tv_address)
        TextView tvAddress;


        public LinearListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



    public interface ContainerClickListener {
        public void onItemShopClickListener(Integer id, String title);
    }
    public void setCustomButtonListner(ContainerClickListener listener) {
        this.customListener = listener;
    }


}
