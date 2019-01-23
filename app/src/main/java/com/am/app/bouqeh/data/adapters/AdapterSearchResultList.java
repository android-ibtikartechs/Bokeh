package com.am.app.bouqeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.am.app.bouqeh.R;
import com.am.app.bouqeh.data.models.ModelProductItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSearchResultList extends ArrayAdapter<ModelProductItem> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ModelProductItem> items;

    private CustomeListener customeListener;


    public AdapterSearchResultList(Context context, ArrayList<ModelProductItem> items)
    {
        super(context,0,items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ModelProductItem searchResultItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_search_item_list, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.tvTitle.setText(searchResultItem.getName());
        viewHolder.tvCategory.setText(searchResultItem.getCategory());
        viewHolder.loutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customeListener.onItemClickListener(searchResultItem);
            }
        });

        return convertView;

    }


    public void add(ModelProductItem r) {
        items.add(r);
        //notifyItemInserted(arrayList.size()-1 );
        notifyDataSetChanged();
    }

    public void addAll(List<ModelProductItem> opResults) {
        items.clear();
        notifyDataSetChanged();
        for (ModelProductItem result : opResults) {
            add(result);
        }
    }



    public class ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_category)
        TextView tvCategory;

        @BindView(R.id.lout_container)
        LinearLayout loutContainer;



        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }


    public interface CustomeListener {
        public void onItemClickListener(ModelProductItem modelProductItem);
    }
    public void setCustomButtonListner(CustomeListener listener) {
        this.customeListener = listener;
    }

}
