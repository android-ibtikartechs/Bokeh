package com.am.app.bouqeh.ui.activities.payment;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface PaymentMvpView extends MvpView {
    void showLoading();
    void hideLoading();
    void transitToSuccessActivity();
}
