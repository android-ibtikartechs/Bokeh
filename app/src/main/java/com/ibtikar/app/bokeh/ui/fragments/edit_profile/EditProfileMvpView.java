package com.ibtikar.app.bokeh.ui.fragments.edit_profile;

import com.ibtikar.app.bokeh.ui.activities.base.MvpView;

public interface EditProfileMvpView extends MvpView {
    void showProgressDialog(String title);
    void hideProgressDialog();
    void afterUbdateSuccess();

    void showDialogStatusOfUpdateProfile(String msg);

    void showToast(String message);

    void populateUserData(String emailAddress, String firstName, String lastName, String mobNum, String birthDate, Integer gender);
}
