package com.am.app.bouqeh.ui.fragments.dialog_buy_options;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface DialogBuyOptionsMvpPresenter <V extends DialogBuyOptionsMvpView> extends MvpPresenter<V> {
    void submitAndAddItem(Integer deliveryOrPickup, Integer areaId, Integer cityId, String textAddress, String deliveryDate, Integer deliveryTime);
    void loadAreasSpinner();

    void disposeRxSubscriber();

    int getSelectedAreaId();

    int getSelectedCityId();

    String getSelectedAreaName();
    String getSelectedCityName();
}
