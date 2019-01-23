package com.am.app.bouqeh.ui.fragments.home;

import com.am.app.bouqeh.data.models.OccasionItemModel;
import com.am.app.bouqeh.data.models.ModelProductItem;
import com.am.app.bouqeh.data.models.ModelShopItem;
import com.am.app.bouqeh.ui.activities.base.MvpView;

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
