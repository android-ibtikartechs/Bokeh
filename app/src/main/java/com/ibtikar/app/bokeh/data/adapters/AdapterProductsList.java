package com.ibtikar.app.bokeh.data.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui_utilities.CustomRecyclerView;
import com.ibtikar.app.bokeh.utils.PaginationAdapterCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterProductsList  extends CustomRecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<ModelProductItem> arrayList;
    private Context context;
    private ContainerProductsItemsClickListener customListener;

    private boolean isLoadingAdded = false;
    private boolean retryPageLoad = false;

    private PaginationAdapterCallback mCallback;


    public AdapterProductsList(Context context,
                                List<ModelProductItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.view_product_list_item, parent, false);
                viewHolder = new ItemVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.view_footer_load, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ModelProductItem modelProductItem = arrayList.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final ItemVH itemVH = (ItemVH) holder;
                itemVH.tvProductName.setText(modelProductItem.getName());
                if (modelProductItem.getIsSameDayDelivery())
                    itemVH.loutIsSameDayDelivery.setVisibility(View.VISIBLE);

                if (!(modelProductItem.getImage().equals("") || modelProductItem.getImage() == null ))
                {
                    Glide.with(context)
                            .load(modelProductItem.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(itemVH.imProduct);
                }

                itemVH.tvPrice.setText(modelProductItem.getPrice().toString());

                itemVH.loutCont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customListener.onItemClickListener(modelProductItem.getId(),
                                modelProductItem.getName(),
                                modelProductItem.getImage(),
                                modelProductItem.getPrice(),
                                modelProductItem.getIsSameDayDelivery(),
                                modelProductItem.getSellername(),
                                modelProductItem.getIsLiked(),
                                modelProductItem.getDetails());
                    }
                });
                break;

            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;

                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) loadingVH.itemView.getLayoutParams();
                layoutParams.setFullSpan(true);
                break;
        }


    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return (position == arrayList.size() -1 && isLoadingAdded) ? LOADING : ITEM;
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

    public void remove(ModelProductItem r) {
        int position = arrayList.indexOf(r);
        if (position > -1) {
            arrayList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
        notifyItemRangeRemoved(0, arrayList.size());
    }
    public ModelProductItem getItem(int position) {
        return arrayList.get(position);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        //add(new OpportunityModel());
        add(getItem(arrayList.size()-1));
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = arrayList.size() - 1;
        ModelProductItem result = getItem(position);

        if (result != null) {
            arrayList.remove(position);
            notifyItemRemoved(position);
        }
    }




    protected class ItemVH extends RecyclerView.ViewHolder {
        @BindView(R.id.lout_cont)
        LinearLayout loutCont;

        @BindView(R.id.im_product)
        ImageView imProduct;

        @BindView(R.id.lout_same_day_delivery)
        LinearLayout loutIsSameDayDelivery;

        @BindView(R.id.tv_product_name)
        TextView tvProductName;

        @BindView(R.id.tv_price)
        TextView tvPrice;



        public ItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }




    protected class LoadingVH extends RecyclerView.ViewHolder {
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;

        public LoadingVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            if (progressBar != null) {
                progressBar.setIndeterminate(true);
                progressBar.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.ColorFoshiac), android.graphics.PorterDuff.Mode.MULTIPLY);
                progressBar.setVisibility(View.VISIBLE);
            }

        }
    }

    public void showRetry(boolean show) {
        retryPageLoad = show;
        notifyItemChanged(arrayList.size() - 1);

    }


    public interface ContainerProductsItemsClickListener {
        public void onItemClickListener(Integer id, String title, String imUrl, Integer price, boolean isSameDayDelivery, String sellerName, boolean isLiked, String description);
    }
    public void setCustomButtonListner(ContainerProductsItemsClickListener listener) {
        this.customListener = listener;
    }

    public void setPagingAdapterCallback (PaginationAdapterCallback pagingAdapterCallback)
    {
        this.mCallback = pagingAdapterCallback;
    }

}
