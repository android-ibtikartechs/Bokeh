package com.am.app.bouqeh.ui.activities.payment;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface PaymentMvpPresenter <V extends PaymentMvpView> extends MvpPresenter<V> {
    void checout(int payType, String referenceNumber);
}
