package com.ibtikar.app.bokeh.ui.activities.payment;

import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

public interface PaymentMvpView extends MvpView {
    void showLoading();
    void hideLoading();
    void transitToSuccessActivity();
}
