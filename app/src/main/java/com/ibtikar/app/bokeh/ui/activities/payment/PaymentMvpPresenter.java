package com.ibtikar.app.bokeh.ui.activities.payment;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface PaymentMvpPresenter <V extends PaymentMvpView> extends MvpPresenter<V> {
    void checout(int payType);
}
