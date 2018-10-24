package com.ibtikar.app.bokeh.ui.activities.products_list;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class ProductsListPresenter <V extends ProductsListMvpView> extends BasePresenter<V> implements ProductsListMvpPresenter<V> {
    public ProductsListPresenter(DataManager dataManager) {
        super(dataManager);
    }

}
