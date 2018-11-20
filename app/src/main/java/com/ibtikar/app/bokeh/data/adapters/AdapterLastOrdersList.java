package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.data.models.ModelLastOrdersListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterLastOrdersList extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterCartList.ContainerCartItemsClickListener{
    private ArrayList<ModelLastOrdersListItem> arrayList;
    private Context context;
    AdapterCartList adapterCartList;
    ArrayList<ModelCartItem> cartItemArrayList;

    public AdapterLastOrdersList(ArrayList<ModelLastOrdersListItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.view_my_orders_lis, parent, false);
        viewHolder = new ItemViewHolder(viewItem);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ModelLastOrdersListItem modelLastOrdersListItem = arrayList.get(position);
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        itemViewHolder.tvTotalPrice.setText(modelLastOrdersListItem.getPrice().toString());
        itemViewHolder.tvNumOfItems.setText(modelLastOrdersListItem.getNumOfItems().toString());
        itemViewHolder.tvDayDate.setText(modelLastOrdersListItem.getDayOfMonth(modelLastOrdersListItem.getDate()));
        itemViewHolder.tvMonthDate.setText(modelLastOrdersListItem.getMonth(modelLastOrdersListItem.getDate()));


        cartItemArrayList = new ArrayList<>();
        itemViewHolder.rvOrderItems.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false));

        adapterCartList = new AdapterCartList(cartItemArrayList, context, true);
        adapterCartList.setCustomButtonListner(this);
        itemViewHolder.rvOrderItems.setAdapter(adapterCartList);
        adapterCartList.notifyDataSetChanged();

        addMoreToCartListAdapter(modelLastOrdersListItem.getItemsList());
    }



    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public void add(ModelLastOrdersListItem r) {
        arrayList.add(r);
        notifyItemInserted(arrayList.size()-1 );
    }

    public void addAll(List<ModelLastOrdersListItem> opResults) {
        for (ModelLastOrdersListItem result : opResults) {
            add(result);
        }
    }


    @Override
    public void onCartItemClickListener(ModelCartItem productItem) {

    }


    public void addMoreToCartListAdapter(final List<ModelCartItem> list) {
        adapterCartList.addAll(list);
    }


    protected class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_day_date)
        TextView tvDayDate;

        @BindView(R.id.tv_month_date)
        TextView tvMonthDate;

        @BindView(R.id.tv_total_price)
        TextView tvTotalPrice;

        @BindView(R.id.tv_num_of_items)
        TextView tvNumOfItems;

        @BindView(R.id.rv_cart_items)
        RecyclerView rvOrderItems;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }


}
