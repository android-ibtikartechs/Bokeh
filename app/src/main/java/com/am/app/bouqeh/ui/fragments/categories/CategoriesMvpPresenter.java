package com.am.app.bouqeh.ui.fragments.categories;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface CategoriesMvpPresenter <V extends CategoriesMvpView> extends MvpPresenter<V> {
    void loadCategories();
}
