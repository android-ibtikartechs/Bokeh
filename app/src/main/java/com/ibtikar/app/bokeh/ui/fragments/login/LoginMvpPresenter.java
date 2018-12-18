package com.ibtikar.app.bokeh.ui.fragments.login;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface LoginMvpPresenter <V extends LoginMvpView> extends MvpPresenter<V> {
    void login(String email, String password);
    void resendActivation(String Email);

}
