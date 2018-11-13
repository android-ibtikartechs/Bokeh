package com.ibtikar.app.bokeh.ui.fragments.shops;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface ShopsMvpPresenter <V extends ShopsMvpView> extends MvpPresenter<V> {
    void loadShops();
}
