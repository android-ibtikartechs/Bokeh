package com.am.app.bouqeh.ui.fragments.categories;

import com.am.app.bouqeh.data.models.OccasionItemModel;
import com.am.app.bouqeh.ui.activities.base.MvpView;

import java.util.List;

public interface CategoriesMvpView extends MvpView {
    void addMoreToCategoryAdapter(List<OccasionItemModel> list);

    // loading
    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
