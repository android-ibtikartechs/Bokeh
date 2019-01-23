package com.am.app.bouqeh.ui.fragments.edit_profile;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface EditProfileMvpPresenter <V extends EditProfileMvpView> extends MvpPresenter<V> {
    void ubdateData(String firstName, String lastName, String mobNum, String birthDate, Integer gender );
    void getUserLocalData();
}
