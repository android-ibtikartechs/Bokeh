package com.am.app.bouqeh.ui.fragments.login;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface LoginMvpView extends MvpView {
    void showProgressDialog(int resourceIdTitle);
    void hideProgressDialog();
    void afterLoginSuccess();

    void showDialogIfForgetPassword();
    void showDialogInvalidData();

    void showDialogRequestActivation();
    void showDialogStatusOfSendingActivation(int resourceIdMessage);

    void showToast(int resourceIdMessage);

}
