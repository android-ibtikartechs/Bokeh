package com.am.app.bouqeh.ui.fragments.forget_password;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface ForgetPasswordMvpPresenter <V extends ForgetPasswordMvpView> extends MvpPresenter<V> {
    void resendPassword(String email);
}
