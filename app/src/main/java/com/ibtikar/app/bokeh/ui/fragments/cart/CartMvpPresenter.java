package com.ibtikar.app.bokeh.ui.fragments.cart;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface CartMvpPresenter<V extends CartMvpView> extends MvpPresenter<V> {
    void loadCartList();
}
