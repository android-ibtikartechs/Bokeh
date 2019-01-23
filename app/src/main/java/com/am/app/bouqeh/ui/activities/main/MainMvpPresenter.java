package com.am.app.bouqeh.ui.activities.main;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface MainMvpPresenter <V extends MainMvpView> extends MvpPresenter<V> {
     Integer getCartItem();
     void refreshCartItemCount();
}
