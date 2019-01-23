package com.am.app.bouqeh.ui.fragments.shops;

import com.am.app.bouqeh.data.models.ModelShopItem;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface ShopsMvpView extends MvpView {
    void addMoreToAdapter(List<ModelShopItem> list);


    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
