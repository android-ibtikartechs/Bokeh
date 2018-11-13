package com.ibtikar.app.bokeh.ui.fragments.dialog_buy_options;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class DialogBuyOptionsPresenter <V extends DialogBuyOptionsMvpView> extends BasePresenter<V> implements DialogBuyOptionsMvpPresenter<V>{
    public DialogBuyOptionsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void submitAndAddItem() {
        getDataManager().plusOneCartItems();
    }
}
