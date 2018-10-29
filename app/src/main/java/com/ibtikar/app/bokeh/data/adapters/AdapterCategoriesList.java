package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.OccasionItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterCategoriesList extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<OccasionItemModel> arrayList;
    private Context context;
    private ContainerClickListener customListener;
    private boolean isGrid;

    public AdapterCategoriesList(List<OccasionItemModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public AdapterCategoriesList(List<OccasionItemModel> arrayList, Context context, boolean isGrid) {
        this.arrayList = arrayList;
        this.context = context;
        this.isGrid = isGrid;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem;
        if (isGrid)
            viewItem = inflater.inflate(R.layout.view_categories_item_grid, parent, false);
        else
            viewItem = inflater.inflate(R.layout.view_categorie_item_horizontal, parent, false);

        viewHolder = new ItemViewHolder(viewItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final OccasionItemModel categoriesListItem = arrayList.get(position);
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        if (!(categoriesListItem.getImage().equals("") || categoriesListItem.getImage() == null ))
        {
            Glide.with(context)
                    .load(categoriesListItem.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemViewHolder.imCategory);
        }
/*
        else if (categoriesListItem.getImBitmab() != null)
        {
            itemViewHolder.imCategory.setImageBitmap(categoriesListItem.getImBitmab());
        }
        */

        itemViewHolder.tvTitle.setText(categoriesListItem.getTitle());
        itemViewHolder.loutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customListener.onItemClickListener(categoriesListItem.getId(),categoriesListItem.getTitle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void add(OccasionItemModel r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(List<OccasionItemModel> opResults) {
        for (OccasionItemModel result : opResults) {
            add(result);
        }
    }


    protected class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.im_category)
        ImageView imCategory;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.lout_container)
        RelativeLayout loutContainer;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ContainerClickListener {
        public void onItemClickListener(Integer id, String title);
    }
    public void setCustomButtonListner(ContainerClickListener listener) {
        this.customListener = listener;
    }
}
