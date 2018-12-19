package com.ibtikar.app.bokeh.ui.fragments.account_content;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface AccountContentMvpPresenter <V extends AccountContentMvpView> extends MvpPresenter<V> {
    void getProfileData();
    void logout();
}
