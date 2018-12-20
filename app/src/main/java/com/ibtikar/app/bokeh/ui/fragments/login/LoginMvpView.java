package com.ibtikar.app.bokeh.ui.fragments.login;

import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

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
