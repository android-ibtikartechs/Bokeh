package com.ibtikar.app.bokeh.ui.activities.product_details;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;
import com.ibtikar.app.bokeh.ui.activities.products_list.ProductsListMvpPresenter;

public class ProductDetailsPresenter <V extends ProductDetailsMvpView> extends BasePresenter<V> implements ProductDetailsMvpPresenter<V> {
    public ProductDetailsPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
