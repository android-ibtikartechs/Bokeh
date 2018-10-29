package com.ibtikar.app.bokeh.ui.fragments.categories;

import com.ibtikar.app.bokeh.data.models.OccasionItemModel;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface CategoriesMvpView extends MvpView {
    void addMoreToCategoryAdapter(List<OccasionItemModel> list);

    // loading
    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
