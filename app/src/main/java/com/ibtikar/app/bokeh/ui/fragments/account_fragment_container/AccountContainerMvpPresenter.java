package com.ibtikar.app.bokeh.ui.fragments.account_fragment_container;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface AccountContainerMvpPresenter <V extends AccountContainerMvpView> extends MvpPresenter<V> {
    void checkLoginStatus();
}
