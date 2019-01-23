package com.am.app.bouqeh.ui.fragments.signup;

import com.am.app.bouqeh.ui.activities.base.MvpView;

public interface SignupMvpView extends MvpView {
    void showProgressDialog(String title);
    void hideProgressDialog();
    void afterSignUpSuccess();

    void showDialogRequestActivation();
    void showDialogThisUserExistedAlredy();
    void showDialogStatusOfSendingActivation(String msg);



    void showToast(String message);
}
