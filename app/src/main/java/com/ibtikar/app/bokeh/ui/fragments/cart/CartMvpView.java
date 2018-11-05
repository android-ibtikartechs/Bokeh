package com.ibtikar.app.bokeh.ui.fragments.cart;

import com.ibtikar.app.bokeh.data.models.ModelCartItem;
import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

import java.util.List;

public interface CartMvpView extends MvpView {
    void addMoreToCartListAdapter(List<ModelCartItem> list);


    void showErrorConnectionView();
    void showLoadingView();
    void showContent();
}
