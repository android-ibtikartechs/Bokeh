package com.ibtikar.app.bokeh.ui.fragments.edit_profile;

import com.ibtikar.app.bokeh.data.DataManager;
import com.ibtikar.app.bokeh.ui.activities.base.BasePresenter;

public class EditProfilePresenter <V extends EditProfileMvpView> extends BasePresenter<V> implements EditProfileMvpPresenter<V> {

    public EditProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void ubdateData(String firstName, String lastName, String mobNum, String birthDate, Integer gender) {

    }

    @Override
    public void getUserLocalData() {
        getMvpView().populateUserData(getDataManager().getUserEmail(), getDataManager().getFirstName(), getDataManager().getLastName(), getDataManager().getUserMobNum(), getDataManager().getBirthDate(),getDataManager().getGender());
    }
}
