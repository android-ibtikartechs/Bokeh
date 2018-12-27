package com.ibtikar.app.bokeh.ui.fragments.edit_profile;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;
import com.ibtikar.app.bokeh.ui.fragments.signup.SignupMvpView;

public interface EditProfileMvpPresenter <V extends EditProfileMvpView> extends MvpPresenter<V> {
    void ubdateData(String firstName, String lastName, String mobNum, String birthDate, Integer gender );
    void getUserLocalData();
}
