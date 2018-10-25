package com.ibtikar.app.bokeh.ui.activities.products_list;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface ProductsListMvpPresenter <V extends ProductsListMvpView> extends MvpPresenter<V> {
    void loadFirstPage();
    void loadNextPage(int currentPage);
}
