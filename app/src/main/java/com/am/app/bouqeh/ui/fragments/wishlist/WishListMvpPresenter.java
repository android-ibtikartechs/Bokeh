package com.am.app.bouqeh.ui.fragments.wishlist;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface WishListMvpPresenter <V extends WishListMvpView> extends MvpPresenter<V> {
    void loadFirstPage();
    void loadNextPage(int currentPage);
}
