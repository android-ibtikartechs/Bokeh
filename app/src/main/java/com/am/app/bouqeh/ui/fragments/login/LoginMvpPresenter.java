package com.am.app.bouqeh.ui.fragments.login;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {
    void login(String email, String password);
    void resendActivation(String Email);

}
