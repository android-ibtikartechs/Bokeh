package com.am.app.bouqeh.ui.fragments.account_content;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface AccountContentMvpPresenter <V extends AccountContentMvpView> extends MvpPresenter<V> {
    void getProfileData();
    void logout();
}
