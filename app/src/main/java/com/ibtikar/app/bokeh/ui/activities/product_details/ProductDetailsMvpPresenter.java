package com.ibtikar.app.bokeh.ui.activities.product_details;

import com.ibtikar.app.bokeh.data.models.ModelProductItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

import java.util.List;

public interface ProductDetailsMvpPresenter <V extends ProductDetailsMvpView> extends MvpPresenter<V> {

    void loadData();
    void changeLikeStaus(Integer productId);
    void addToCart();
}
