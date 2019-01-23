package com.am.app.bouqeh.ui.fragments.home;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {
    void loadHomeData();

}
