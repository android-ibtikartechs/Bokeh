package com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface DialogBuyOptionsMvpPresenter <V extends DialogBuyOptionsMvpView> extends MvpPresenter<V> {
    void submitAndAddItem();
    void loadAreasSpinner();
}
