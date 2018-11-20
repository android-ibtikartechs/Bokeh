package com.ibtikar.app.bokeh.ui.fragments.my_orders;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface MyOrdersMvpPresenter<V extends MyOrdersMvpView> extends MvpPresenter<V> {
    void loadOrdersList();
}
