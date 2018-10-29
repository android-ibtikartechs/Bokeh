package com.ibtikar.app.bokeh.ui.fragments.categories;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface CategoriesMvpPresenter <V extends CategoriesMvpView> extends MvpPresenter<V> {
    void loadCategories();
}
