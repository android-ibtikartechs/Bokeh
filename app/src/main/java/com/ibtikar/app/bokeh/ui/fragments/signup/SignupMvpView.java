package com.ibtikar.app.bokeh.ui.fragments.signup;

import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

public interface SignupMvpView extends MvpView {
    void showProgressDialog(String title);
    void hideProgressDialog();
    void afterSignUpSuccess();

    void showDialogRequestActivation();
    void showDialogThisUserExistedAlredy();
    void showDialogStatusOfSendingActivation(String msg);



    void showToast(String message);
}
