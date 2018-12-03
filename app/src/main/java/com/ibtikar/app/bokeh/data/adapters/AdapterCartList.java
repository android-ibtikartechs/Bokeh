package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelCartListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCartList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ModelCartListItem> arrayList;
    private Context context;
    private ContainerCartItemsClickListener customListener;
    private boolean isApartOfLastOrder;


    public AdapterCartList(ArrayList<ModelCartListItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public AdapterCartList(ArrayList<ModelCartListItem> arrayList, Context context, boolean isApartOfLastOrder) {
        this.arrayList = arrayList;
        this.context = context;
        this.isApartOfLastOrder = isApartOfLastOrder;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem;
        if (isApartOfLastOrder)
            viewItem = inflater.inflate(R.layout.view_item_order_list_of_items, parent, false);
        else
            viewItem = inflater.inflate(R.layout.view_card_cart, parent, false);

        viewHolder = new ItemViewHolder(viewItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ModelCartListItem modelCartItem = arrayList.get(position);
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        if (!(modelCartItem.getProductInfo().getImage().equals("") || modelCartItem.getProductInfo().getImage() == null ))
        {
            Glide.with(context)
                    .load(modelCartItem.getProductInfo().getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
                    .into(itemViewHolder.imProduct);
        }

        itemViewHolder.tvProductName.setText(modelCartItem.getProductInfo().getName());
        itemViewHolder.tvDeliveryDate.setText(modelCartItem.getDelivary().getDate());
        //itemViewHolder.tvDeliveryTime.setText(modelCartItem.getDeliveryTime());

        if (modelCartItem.getDelivary().getTime()==1)
            itemViewHolder.tvDeliveryTime.setText("1:00 am - 2:00 pm");

        else
            itemViewHolder.tvDeliveryTime.setText("4:00 pm - 11:30 pm");

        if (modelCartItem.getDelivary().getType() == 1)
            itemViewHolder.tvDeliveryOrPickup.setText("Delivery");

        else
            itemViewHolder.tvDeliveryOrPickup.setText("Pickup");

        itemViewHolder.tvSellerName.setText(modelCartItem.getProductInfo().getSellername());
        itemViewHolder.tvPrice.setText(modelCartItem.getProductInfo().getPrice().toString());
        itemViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, arrayList.size());
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void add(ModelCartListItem r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(List<ModelCartListItem> opResults) {
        for (ModelCartListItem result : opResults) {
            add(result);
        }
    }







    protected class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.lout_container)
        CardView loutContainer;

        @BindView(R.id.im_product)
        ImageView imProduct;

        @BindView(R.id.tv_product_name)
        TextView tvProductName;

        @BindView(R.id.tv_seller_name)
        TextView tvSellerName;

        @BindView(R.id.tv_delivery_date)
        TextView tvDeliveryDate;

        @BindView(R.id.tv_delivery_time)
        TextView tvDeliveryTime;

        @BindView(R.id.btn_remove_quantity)
        ImageView btnRemoveQuantity;

        @BindView(R.id.tv_quantity)
        TextView tvQuantity;

        @BindView(R.id.btn_add_quantity)
        ImageView btnAddQuantity;

        @BindView(R.id.tv_price)
        TextView tvPrice;

        @BindView(R.id.btn_delete)
        Button btnDelete;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        @BindView(R.id.tv_delivery_or_pickup)
        TextView tvDeliveryOrPickup;

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



    public interface ContainerCartItemsClickListener {
        public void onCartItemClickListener(ModelCartItem productItem);
    }
    public void setCustomButtonListner(ContainerCartItemsClickListener listener) {
        this.customListener = listener;
    }
}
