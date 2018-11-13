package com.ibtikar.app.bokeh.ui.fragments.shops;

import com.ibtikar.app.bokeh.data.models.ModelShopItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface ShopsMvpView extends MvpView {
    void addMoreToAdapter(List<ModelShopItem> list);


    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
