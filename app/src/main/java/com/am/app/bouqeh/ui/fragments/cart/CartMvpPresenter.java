package com.am.app.bouqeh.ui.fragments.cart;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface CartMvpPresenter<V extends CartMvpView> extends MvpPresenter<V> {
    void loadCartList();
    void loadReceitList();
    void increaseCartItemQuantityPresenter(int cartItemId, int position, Integer currentQuantity);
    void decreaseCartItemQuantityPresenter(int cartItemId, int position, Integer currentQuantity);
    void deleteItemFromCartList(int cartItemId, int position);

    void buy(Integer userId);

}
