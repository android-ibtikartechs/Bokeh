package com.ibtikar.app.bokeh.ui.activities.country;

import com.ibtikar.app.bokeh.ui.activities.base.MvpPresenter;

public interface CountrySelectionMvpPresenter  <V extends CountrySelectionMvpView> extends MvpPresenter<V> {
    void loadCitiesList();
    void setSelectedArea(int selectedCountryId);
    void checkUpdateStatus(int currentVersionCode);
}
