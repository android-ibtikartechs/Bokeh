package com.am.app.bouqeh.ui.activities.country;

import com.am.app.bouqeh.ui.activities.base.MvpPresenter;

public interface CountrySelectionMvpPresenter  <V extends CountrySelectionMvpView> extends MvpPresenter<V> {
    void loadCitiesList();
    void setSelectedArea(int selectedAreaId);
    void setSelectedCity(int selectedCityId);
    void checkUpdateStatus(int currentVersionCode);
}
