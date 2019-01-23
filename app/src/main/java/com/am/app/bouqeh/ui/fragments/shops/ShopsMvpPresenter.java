package com.am.app.bouqeh.ui.fragments.shops;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface ShopsMvpPresenter <V extends ShopsMvpView> extends MvpPresenter<V> {
    void loadShops();
}
