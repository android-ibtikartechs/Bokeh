package com.am.app.bouqeh.ui.activities.main;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface MainMvpView extends MvpView {
    void refreshCartCount(Integer cartItemsCount);
}
