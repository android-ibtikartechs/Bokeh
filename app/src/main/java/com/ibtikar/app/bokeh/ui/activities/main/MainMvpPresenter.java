package com.ibtikar.app.bokeh.ui.activities.main;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface MainMvpPresenter <V extends MainMvpView> extends MvpPresenter<V> {
     Integer getCartItem();
}
