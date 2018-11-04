package com.ibtikar.app.bokeh.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibtikar.app.bokeh.R;
import com.ibtikar.app.bokeh.data.models.ModelSearchResultItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSearchResultList extends ArrayAdapter<ModelSearchResultItem> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<ModelSearchResultItem> items;

    private CustomeListener customeListener;


    public AdapterSearchResultList(Context context, ArrayList<ModelSearchResultItem> items)
    {
        super(context,0,items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ModelSearchResultItem searchResultItem = getItem(position);
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
                customeListener.onItemClickListener(searchResultItem.getId());
            }
        });

        return convertView;

    }


    public void add(ModelSearchResultItem r) {
        items.add(r);
        //notifyItemInserted(arrayList.size()-1 );
        notifyDataSetChanged();
    }

    public void addAll(List<ModelSearchResultItem> opResults) {
        items.clear();
        notifyDataSetChanged();
        for (ModelSearchResultItem result : opResults) {
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
        public void onItemClickListener(Integer productId);
    }
    public void setCustomButtonListner(CustomeListener listener) {
        this.customeListener = listener;
    }

}
