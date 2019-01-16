package com.ibtikar.app.bokeh.ui.fragments.forget_password;

import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

public interface ForgetPasswordMvpView extends MvpView {
    void showProgressDialog(String title);
    void hideProgressDialog();

    void afterResendSuccess();

    void showDialogInvalidData();

    void showToast(String message);
}
