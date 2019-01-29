package com.am.app.bouqeh.ui.activities.country;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface CountrySelectionMvpPresenter  <V extends CountrySelectionMvpView> extends MvpPresenter<V> {
    void loadCitiesList();
    void setSelectedArea(int selectedAreaId, String selectedAreaName);
    void setSelectedCity(int selectedCityId, String selectedCityName);
    void checkUpdateStatus(int currentVersionCode);
}
