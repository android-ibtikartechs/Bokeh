package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.ModelProductItemReciptList;
import com.ibtikar.app.bokeh.data.models.ModelReciptList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterReciptList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ModelReciptList> arrayList;
    private Context context;
    AdapterProductItemReciptList adapterProductItemReciptList;
    ArrayList<ModelProductItemReciptList> productItemReciptList;

    public AdapterReciptList(ArrayList<ModelReciptList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.view_recipt_list_item, parent, false);
        viewHolder = new ItemViewHolder(viewItem);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ModelReciptList modelReciptList = arrayList.get(position);
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.tvDeliveryFees.setText(modelReciptList.getOrderInfo().getDelivaryFees()+" EGP");
        itemViewHolder.tvSubtotalPrice.setText(modelReciptList.getOrderInfo().getTotal() + " EGP");


        productItemReciptList = new ArrayList<>();
        itemViewHolder.rvProductsList.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false));

        adapterProductItemReciptList = new AdapterProductItemReciptList(productItemReciptList, context);
        itemViewHolder.rvProductsList.setAdapter(adapterProductItemReciptList);
        adapterProductItemReciptList.notifyDataSetChanged();

        addMoreToCartListAdapter(modelReciptList.getProducts());
    }





    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void add(ModelReciptList r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(List<ModelReciptList> opResults) {
        for (ModelReciptList result : opResults) {
            add(result);
        }
    }

    public void addMoreToCartListAdapter(final List<ModelProductItemReciptList> list) {
        adapterProductItemReciptList.addAll(list);
    }

    protected class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rv_products_list)
        RecyclerView rvProductsList;

        @BindView(R.id.tv_delivery_fees)
        TextView tvDeliveryFees;

        @BindView(R.id.tv_subtotal_price)
        TextView tvSubtotalPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

}
