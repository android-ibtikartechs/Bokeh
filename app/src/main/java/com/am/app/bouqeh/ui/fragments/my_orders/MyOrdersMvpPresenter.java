package com.am.app.bouqeh.ui.fragments.my_orders;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface MyOrdersMvpPresenter<V extends MyOrdersMvpView> extends MvpPresenter<V> {
    void loadOrdersList();
}
