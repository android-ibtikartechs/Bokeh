package com.am.app.bouqeh.ui.fragments.signup;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface SignupMvpPresenter <V extends SignupMvpView> extends MvpPresenter<V> {
    void signup(String firstName, String lastName, String mobNum, String email, String password, String confirmPassword);
    void resendActivation(String Email);
}
