package com.ibtikar.app.bokeh.ui.fragments.home;

import com.ibtikar.app.bokeh.data.models.OccasionItemModel;
import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.ArrayList;
import java.util.List;

public interface HomeMvpView extends MvpView {
    void addMoreToCategoryAdapter(List<OccasionItemModel> list);
    void addMoreToFeaturedItemAdapter(List<ModelProductItem>list);
    void addMoreToShopsAdapter(List<ModelShopItem>list);
    void addListToSlider(List<ModelProductItem> products);

    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
