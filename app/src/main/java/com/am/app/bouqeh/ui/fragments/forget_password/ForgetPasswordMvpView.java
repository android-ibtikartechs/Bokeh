package com.am.app.bouqeh.ui.fragments.forget_password;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface ForgetPasswordMvpView extends MvpView {
    void showProgressDialog(String title);
    void hideProgressDialog();

    void afterResendSuccess();

    void showDialogInvalidData();

    void showToast(String message);
}
