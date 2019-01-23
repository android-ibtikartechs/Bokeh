package com.am.app.bouqeh.ui.fragments.login;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface LoginMvpView extends MvpView {
    void showProgressDialog(String title);
    void hideProgressDialog();
    void afterLoginSuccess();

    void showDialogIfForgetPassword();
    void showDialogInvalidData();

    void showDialogRequestActivation();
    void showDialogStatusOfSendingActivation(String msg);

    void showToast(String message);

}
