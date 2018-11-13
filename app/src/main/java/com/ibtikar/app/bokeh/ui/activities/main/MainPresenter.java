package com.ibtikar.app.bokeh.ui.activities.main;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public Integer getCartItem() {
        return getDataManager().getCartItemsCount();
    }
}
