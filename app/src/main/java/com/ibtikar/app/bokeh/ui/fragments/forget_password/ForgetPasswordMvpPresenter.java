package com.ibtikar.app.bokeh.ui.fragments.forget_password;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface ForgetPasswordMvpPresenter <V extends ForgetPasswordMvpView> extends MvpPresenter<V> {
    void resendPassword(String email);
}
