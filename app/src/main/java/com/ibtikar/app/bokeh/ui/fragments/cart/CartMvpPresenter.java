package com.ibtikar.app.bokeh.ui.fragments.cart;

import com.ibtikar.app.bokeh.data.models.ModelReciptList;
import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

import java.util.List;

public interface CartMvpPresenter<V extends CartMvpView> extends MvpPresenter<V> {
    void loadCartList();
    void increaseCartItemQuantityPresenter(int cartItemId, int position, Integer currentQuantity);
    void decreaseCartItemQuantityPresenter(int cartItemId, int position, Integer currentQuantity);

}
