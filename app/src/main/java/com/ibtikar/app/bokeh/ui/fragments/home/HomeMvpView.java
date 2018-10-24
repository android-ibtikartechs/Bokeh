package com.ibtikar.app.bokeh.ui.fragments.home;

import com.ibtikar.app.bokeh.data.models.CategoryItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.ArrayList;
import java.util.List;

public interface HomeMvpView extends MvpView {
    void addMoreToCategoryAdapter(ArrayList<CategoryItemModel> list);
    void addMoreToFeaturedItemAdapter(ArrayList<ModelProductItem>list);
    void addMoreToShopsAdapter(ArrayList<ModelShopItem>list);
    void addListToSlider(List<ModelProductItem> products);
}
