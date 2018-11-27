package com.ibtikar.app.bokeh.ui.fragments.wishlist;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface WishListMvpPresenter <V extends WishListMvpView> extends MvpPresenter<V> {
    void loadFirstPage();
    void loadNextPage(int currentPage);
}
