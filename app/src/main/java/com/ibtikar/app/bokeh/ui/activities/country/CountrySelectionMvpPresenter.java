package com.ibtikar.app.bokeh.ui.activities.country;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface CountrySelectionMvpPresenter  <V extends CountrySelectionMvpView> extends MvpPresenter<V> {
    void loadCitiesList();
    void setSelectedArea(int selectedAreaId);
    void setSelectedCity(int selectedCityId);
    void checkUpdateStatus(int currentVersionCode);
}
