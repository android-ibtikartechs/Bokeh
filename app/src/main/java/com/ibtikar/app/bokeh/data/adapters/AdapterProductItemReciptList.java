package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.ModelProductItemReciptList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterProductItemReciptList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ModelProductItemReciptList> arrayList;
    private Context context;

    public AdapterProductItemReciptList(ArrayList<ModelProductItemReciptList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem;

        viewItem = inflater.inflate(R.layout.view_product_recipt_list, parent, false);

        viewHolder = new ItemViewHolder(viewItem);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final ModelProductItemReciptList modelProductItemReciptList = arrayList.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.tvProductName.setText(modelProductItemReciptList.getName());
        itemViewHolder.tvPrice.setText(modelProductItemReciptList.getPrice().toString());

    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void add(ModelProductItemReciptList r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(List<ModelProductItemReciptList> opResults) {
        for (ModelProductItemReciptList result : opResults) {
            add(result);
        }
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_product_name)
        TextView tvProductName;

        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }


}
