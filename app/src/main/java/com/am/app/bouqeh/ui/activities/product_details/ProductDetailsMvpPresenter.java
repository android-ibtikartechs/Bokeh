package com.am.app.bouqeh.ui.activities.product_details;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface ProductDetailsMvpPresenter <V extends ProductDetailsMvpView> extends MvpPresenter<V> {

    void loadData();
    void changeLikeStaus(Integer productId);
    void addToCart();

    void isProductLiked(int productId);
}
