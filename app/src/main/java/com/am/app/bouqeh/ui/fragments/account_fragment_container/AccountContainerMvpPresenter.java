package com.am.app.bouqeh.ui.fragments.account_fragment_container;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface AccountContainerMvpPresenter <V extends AccountContainerMvpView> extends MvpPresenter<V> {
    void checkLoginStatus();
}
