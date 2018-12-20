package com.ibtikar.app.bokeh.ui.fragments.signup;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface SignupMvpPresenter <V extends SignupMvpView> extends MvpPresenter<V> {
    void signup(String firstName, String lastName, String mobNum, String email, String password, String confirmPassword);
    void resendActivation(String Email);
}
